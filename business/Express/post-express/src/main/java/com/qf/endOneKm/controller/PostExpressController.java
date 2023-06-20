package com.qf.endOneKm.controller;


import com.qf.endOneKm.R;
import com.qf.endOneKm.dto.PostExpress;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.service.IPostExpressService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/postExpress")
public class PostExpressController {

    @Autowired
    private IPostExpressService postExpressService;



    @GetMapping("/list")
    public R list(){
        List<PostExpress> list = postExpressService.list();

        if(list==null||!(list.size()>0)){
            throw new BusinessException(500,"没有驿站快递信息");
        }

        return R.ok().put("data",list);
    }

    @GetMapping("/test2")
    public R test2(){

        System.out.println(RootContext.getXID());

        Map admin=new HashMap();
        admin.put("name","小刚");
        admin.put("id","1");
        postExpressService.updateById2(admin);



        return R.ok();
    }


}
