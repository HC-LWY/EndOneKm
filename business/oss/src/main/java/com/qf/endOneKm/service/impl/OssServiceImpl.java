package com.qf.endOneKm.service.impl;

import com.qf.endOneKm.mapper.OssMapper;
import com.qf.endOneKm.service.IOssService;
import com.qf.endOneKm.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OssServiceImpl implements IOssService {

    @Autowired
    private OssMapper ossMapper;


    @Override
    public Admin getAdminByAccount(String account) {
        return ossMapper.selectByAccount(account);
    }
}
