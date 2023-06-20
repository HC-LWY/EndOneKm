package com.qf.endOneKm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.endOneKm.dto.Admin;

import java.util.Map;

public interface IAdminService extends IService<Admin> {
    boolean delAdmin(String accountName);

    Admin getAdminAccountByName(String userName);

    void updateById2(Map admin);
}
