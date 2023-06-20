package com.qf.endOneKm.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("t_admin")
public class Admin {

    @TableId
    @TableField("account_name")
    private String accountName;
    private String password;
    @TableField("user_name")
    private String userName;
    @TableField("user_phone")
    private String userPhone;
    @TableField("site_name")
    private String siteName;
    @TableField("user_score")
    private Integer userScore;
    private String jurisdiction;
    @TableField("operation_pass")
    private String operationPass;
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("user_status")
    private Integer userStatus;


}
