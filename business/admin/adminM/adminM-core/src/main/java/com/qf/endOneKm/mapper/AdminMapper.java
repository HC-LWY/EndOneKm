package com.qf.endOneKm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.endOneKm.dto.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends BaseMapper<Admin> {
    Integer delStatus(String accountName);


    Admin selectByUserName(String userName);

    void update2(@Param("id") String id,@Param("name") String name);
}
