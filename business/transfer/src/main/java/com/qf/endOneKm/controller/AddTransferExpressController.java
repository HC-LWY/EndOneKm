package com.qf.endOneKm.controller;


import com.qf.end.OneKm.dto.TransferExpress;
import com.qf.endOneKm.R;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.service.IAddTransferExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addTransferExpress")
public class AddTransferExpressController {

    @Autowired
    private IAddTransferExpressService addTransferExpressService;


    @PostMapping("/add")
    public R addTransferExpress(TransferExpress transferExpress){


        boolean b = addTransferExpressService.addTransferExpress(transferExpress);


        if(!b){
            throw new BusinessException(400,"入库失败");
        }

        return R.ok().put("msg","入库成功");
    }



}
