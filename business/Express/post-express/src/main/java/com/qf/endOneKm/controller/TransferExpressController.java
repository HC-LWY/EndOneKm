package com.qf.endOneKm.controller;


import com.qf.end.OneKm.dto.TransferExpress;
import com.qf.endOneKm.R;
import com.qf.endOneKm.dto.PostExpress;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.service.IPostExpressService;
import com.qf.endOneKm.service.ITransferExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transferExpress")
public class TransferExpressController {

    @Autowired
    private ITransferExpressService transferExpressService;



    @GetMapping("/list")
    public R list(){
        List<TransferExpress> list = transferExpressService.list();

        if(list==null||!(list.size()>0)){
            throw new BusinessException(500,"没有中转站快递信息");
        }

        return R.ok().put("data",list);
    }


}
