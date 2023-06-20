package com.qf.endOneKm.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data

@TableName("t_transfer")
public class Transfer {

    @TableId(type= IdType.AUTO)
    @TableField("transfer_id")
    private Long transferId;
    @TableField("transfer_name")
    private String transferName;
    @TableField("transfer_ads")
    private String transferAds;
    @TableField("transfer_status")
    private Integer transferStatus;
    @TableField("transfer_remarks")
    private String transferRemarks;
    @TableField("transfer_charge")
    private String transferCharge;
    @TableField("transfer_charge_phone")
    private String transferChargePhone;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("transfer_affiliation")
    private String transferAffiliation;
    @TableField("transfer_charge_id")
    private String transferChargeId;


}
