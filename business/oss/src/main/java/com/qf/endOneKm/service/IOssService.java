package com.qf.endOneKm.service;


import com.qf.endOneKm.dto.Admin;

public interface IOssService {
    Admin getAdminByAccount(String account);
}
