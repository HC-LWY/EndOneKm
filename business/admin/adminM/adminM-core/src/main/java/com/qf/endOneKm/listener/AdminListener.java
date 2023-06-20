package com.qf.endOneKm.listener;


import com.qf.endOneKm.dto.Admin;
import com.qf.endOneKm.service.IAdminService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class AdminListener {

    @Autowired
    private IAdminService adminService;


    @RabbitListener(queues = {"putAdminQueue"})
    public void putPostAdmin(Map map){
        //收到通知，修改相关管理员信息
        Admin admin = adminService.getById((String) map.get("chargeId"));
        admin.setJurisdiction((String) map.get("jurisdiction"));
        admin.setSiteName((String) map.get("siteName"));
        admin.setUserStatus((Integer) map.get("userStatus"));
        adminService.updateById(admin);

    }


    @RabbitListener(queues = {"delAdminQueue"})
    public void delPostAdmin(String accountName){

        //收到通知，修改相关管理员信息
        adminService.delAdmin(accountName);
    }



}
