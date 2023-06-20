package com.qf.endOneKm.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.qf.endOneKm.R;
import com.qf.endOneKm.dto.Admin;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.feign.AdminMFeign;
import com.qf.endOneKm.service.IAdminService;
//import io.seata.spring.annotation.GlobalTransactional;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private RestTemplate restTemplate;
//
    @Autowired
    private AdminMFeign adminMFeign;


    @Value("${server.port}")
    String port;


//    @Value("${name}")
//    String name;

    @GetMapping("/test")
//    @Transactional
    @GlobalTransactional(rollbackFor = Exception.class)
    public R test(){
        System.out.println(RootContext.getXID());
        restTemplate.getForEntity("http://express-server/postExpress/test2",null);
        Admin admin=new Admin();
        admin.setAccountName("alb_z");
        admin.setPassword("alb_z");
        admin.setUserName("jk");
        admin.setUserPhone("15178569865");
        admin.setUserStatus(1);
        admin.setSiteName("阿里霸霸中转站");
        adminService.updateById(admin);
        int a=1/0;
//        adminMFeign.test2();

        return R.ok();

    }




    @GetMapping("/{name}")
    @SentinelResource("admin_name")
    public String getId(@PathVariable String name){
        return name;
    }


    public static void main(String[] args) {
        ArrayList arrayList=new ArrayList();
    }


//    @LogonValidate
    @GetMapping("/list")
    @SentinelResource("admin_list")
    public R list(){




        //测试负载均衡策略
//        System.out.println(port);


        //1.查询所有中转站信息
//        List<Admin> list = adminService.list();
//
//        //2.防止返回空集合到前端
//        if(list.size()==0||list==null){
//            throw new BusinessException(500,"没有管理员数据");
//        }

        return adminMFeign.test();
    }

    @PostMapping("/addAdmin")
    public R addAdmin(Admin admin){
        //1.校验数据合法性
        admin.setCreateTime(new Date());
        if(admin.getJurisdiction()==null||admin.getJurisdiction().equals("")){
            admin.setJurisdiction("待定");
        }

        //2.注册管理员
        boolean save = adminService.save(admin);

        if(!save){
            throw new BusinessException(500,"添加失败");
        }
        //3.响应前端
        return R.ok().put("msg","管理员注册成功");
    }

    @PutMapping("/putAdmin")
    public R putAdmin(Admin admin){
        //1.校验数据合法性



        //2.执行修改操作
        boolean b = adminService.updateById(admin);

        //3.响应前端
        if(!b){
            throw new BusinessException(500,"修改失败");
        }

        return R.ok().put("msg","修改成功");
    }

    @DeleteMapping("/{accountName}")
    public R delAdmin(@PathVariable String accountName){
        //1.检查该管理员状态
        Admin admin = adminService.getById(accountName);
        if(admin==null){
            throw new BusinessException(500,"没有该管理员");
        }else if(admin.getUserStatus()==0){
            throw new BusinessException(500,"该管理员信息已被注销");
        }

        //2.执行注销操作
        boolean b=adminService.delAdmin(accountName);

        //3.响应前端
        if(!b){
            throw new BusinessException(500,"注销失败");
        }

        return R.ok().put("msg","管理员注销成功");
    }
}
