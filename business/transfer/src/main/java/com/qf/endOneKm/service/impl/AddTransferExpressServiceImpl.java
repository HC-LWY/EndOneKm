package com.qf.endOneKm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.end.OneKm.dto.TransferExpress;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.mapper.AddTransferExpressMapper;
import com.qf.endOneKm.service.IAddTransferExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

@Service
public class AddTransferExpressServiceImpl extends ServiceImpl<AddTransferExpressMapper,TransferExpress> implements IAddTransferExpressService {


    @Autowired
    private AddTransferExpressMapper addTransferExpressMapper;

    @Override
    public boolean addTransferExpress(TransferExpress transferExpress) {
        //1.通过单号查询快递信息判断能否入库
        String transferName=addTransferExpressMapper.selectTransferNameById(transferExpress.getTransferId());

        Map b=wo(transferExpress.getExpressId(),transferName);
        //2.封装好中转站快递入库信息
        transferExpress.setWarehousingTime(new Date());


        String transfer = getTransfer((String) b.get("address"));
        if(transfer==null){
            throw new BusinessException(500,"收货地址没有中转站");
        }

        if(transfer.equals(transferExpress.getSiteName())){
            transferExpress.setOutbound(getOutbound((String) b.get("address")));
        }else {
            transferExpress.setOutbound(transfer);
        }
        transferExpress.setAddress((String) b.get("address"));


        transferExpress.setPhone((String) b.get("phone"));
        transferExpress.setPostType((String) b.get("post_type"));
        transferExpress.setSender((String) b.get("sender"));
        transferExpress.setStartAddress((String) b.get("start_address"));
        transferExpress.setWarehousing((String) b.get("site_name"));

        //3.入库

        updateStatus(transferExpress.getExpressId(),(String) b.get("site_name"),new Date());

        Integer i=baseMapper.insert(transferExpress);


        return i>0;


    }

    public Map wo(Long expressId,String transferName){

        Map postExpress=addTransferExpressMapper.selectPostExpress(expressId,transferName);
        Map transferExpress=addTransferExpressMapper.selectTransferExpress(expressId,transferName);

        if(postExpress==null&&transferExpress==null){
            throw new BusinessException(400,"该快递不应该在本地区入库");
        }

        if(postExpress!=null&&transferExpress!=null){
            String postName= (String) postExpress.get("post_name");
            throw new BusinessException(400,postName+"驿站绑定中转站信息异常");
        }

        if(postExpress!=null){
            return postExpress;
        }

        return transferExpress;

    }

    public String getTransfer(String outTown){
        int index = outTown.indexOf("镇");
        String town = outTown.substring(0,index+1);
        return addTransferExpressMapper.selectTransfer(town);
    }

    public String getOutbound(String outTown){
        int index = outTown.indexOf("村");
        String town = outTown.substring(0,index+1);
        return addTransferExpressMapper.selectPost(town);
    }



    public boolean updateStatus(Long expressId,String warehousing,Date outTime){
        Integer i=addTransferExpressMapper.updatePStatus(expressId,warehousing,outTime);
        Integer i2=addTransferExpressMapper.updateTStatus(expressId,warehousing,outTime);
        return i+i2>0;
    }
}
