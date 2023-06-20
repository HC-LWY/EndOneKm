package com.qf.endOneKm.mapper;


import com.qf.endOneKm.dto.Admin;

public interface OssMapper {

    Admin selectByAccount(String account);
}
