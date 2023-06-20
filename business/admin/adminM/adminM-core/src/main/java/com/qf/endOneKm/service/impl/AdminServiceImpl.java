package com.qf.endOneKm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.endOneKm.dto.Admin;
import com.qf.endOneKm.mapper.AdminMapper;
import com.qf.endOneKm.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public boolean delAdmin(String accountName) {

        Integer i=adminMapper.delStatus(accountName);
        return i>0;
    }

    @Override
    public Admin getAdminAccountByName(String userName) {

        Admin admin=adminMapper.selectByUserName(userName);
        return admin;
    }

    @Override
    public void updateById2(Map admin) {

        adminMapper.update2((String)admin.get("id"),(String)admin.get("name"));
    }
}
