package com.qf.endOneKm.controller;


import com.qf.endOneKm.R;
import com.qf.endOneKm.dto.PostExpress;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.service.IPostExpressService;
import com.qf.endOneKm.vo.ExpressOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/postExpress")
public class PostExpressController {

    @Autowired
    private IPostExpressService postExpressService;


    /**
     * 扫码下单接口
     * @param postExpress
     * @return
     */
    @PostMapping("/addPostExpress")
    public R addPostExpress(PostExpress postExpress){

        Long expressId=postExpressService.addPostExpress(postExpress);

        return R.ok().put("msg","下单成功").put("单号为",expressId);
    }

    @PostMapping("/addPostExpress2")
    public R addPostExpress2(PostExpress postExpress){

        boolean b = postExpressService.addPostExpress2(postExpress);
        if(b){

        }
        return R.ok().put("msg","入库成功");
    }

    @PostMapping("/outExpress")
    public R outExpress(ExpressOutVo expressOutVo){

        boolean b = postExpressService.outPostExpress(expressOutVo);
        if(!b){
            throw new BusinessException(500,"您的电话号码与快递信息不符合");
        }
        return R.ok().put("msg","出库成功，请取走包裹");

    }


}
