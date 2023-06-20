package com.qf.endOneKm.controller;



import com.qf.endOneKm.service.ITransferService;
import com.qf.endOneKm.dto.Transfer;
import com.qf.endOneKm.R;
import com.qf.endOneKm.exception.BusinessException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private ITransferService transferService;

    @Autowired
    private RabbitTemplate rabbitTemplate;




    @GetMapping("/list")
    public R list(){
        //1.查询所有中转站信息
        List<Transfer> list = transferService.list();

        //2.防止返回空集合到前端
        if(list.size()==0||list==null){
            throw new BusinessException(500,"没有中转站数据");
        }


        return R.ok().put("data",list);
    }


    /**
     * @param transfer 中转站对象--注册信息
     * @return
     */
    @PostMapping("/addTransfer")
    public R addTransfer(Transfer transfer){
        //1.校验数据合法性
        transfer.setCreateTime(new Date());
        List transfers = transferService.getTransferByTown(transfer.getTransferAffiliation());
        if(transfers!=null||transfers.size()>0){
            throw new BusinessException(500,"该地区已有中转站了");
        }
        //2.将站点信息注册
        boolean save = transferService.save(transfer);

        //3.响应前端
        if(!save){
            throw new BusinessException(500,"中转站注册失败");
        }
        return R.ok().put("msg","中转站注册成功");
    }

    /**
     *
     * @param transferId 中转站ID
     * @return
     */
    @DeleteMapping("/{transferId}")
    public R delTransfer(@PathVariable Integer transferId){
        //1.判断中转站状态
        Transfer transfer = transferService.getById(transferId);
        if(transfer==null){
            throw new BusinessException(500,"没有该中转站信息");
        }else if(transfer.getTransferStatus()==0){
            throw new BusinessException(500,"该中转站已注销");
        }
        //2.执行中转站注销操作
        boolean del=transferService.delTransfer(transferId);


        //3.通知后台修改相关管理员信息
        rabbitTemplate.convertAndSend("myAdminExchange","/del",transfer.getTransferChargeId());


        //4.响应前端
        if(!del){

        }
        return R.ok().put("msg","注销成功");
    }

    @PutMapping("/putTransfer")
    public R putTransfer(Transfer transfer){
        //1.校验数据合法性
        String transferCharge = transfer.getTransferCharge();
        Map map1=transferService.getAccountByName(transferCharge);
        if(transferCharge!=null||!transferCharge.equals("")){
            if(map1==null){
                throw new BusinessException(500,"没有该管理员");
            }
            transfer.setTransferChargeId((String) map1.get("account_name"));
        }

        if(((Integer)map1.get("user_status"))!=0&&!((String)map1.get("site_name")).equals(transfer.getTransferName())){
            throw new BusinessException(500,"所修改的管理员还没有离职");
        }

        boolean b1 = transferService.currentStatus(transfer.getTransferId(),(String)map1.get("user_name"));

        if(b1){
            throw new BusinessException(500,"当前本站管理员还未离职");
        }


        //2.修改中转站信息
        boolean b = transferService.updateById(transfer);
        if(!b){
            throw new BusinessException(500,"修改终转站信息失败");
        }

        //3.通知后台修改相关管理员信息

        Map map=new HashMap();
        map.put("chargeId",transfer.getTransferChargeId());
        map.put("siteName",transfer.getTransferName());
        map.put("userStatus",1);
        if(((String)map1.get("jurisdiction")).equals("超级管理员")){
            map.put("jurisdiction","超级管理员");
        }else {
            map.put("jurisdiction", "中转站");
        }
        rabbitTemplate.convertAndSend("myAdminExchange","/put",map);

        //4.响应前端

        return R.ok().put("msg","修改中转站信息成功");
    }


    @GetMapping("/getTransferByTown/{town}")
    public R getTransfer(@PathVariable String town){
        List transfers=transferService.getTransferByTown(town);
        return R.ok().put("data",transfers);
    }


}
