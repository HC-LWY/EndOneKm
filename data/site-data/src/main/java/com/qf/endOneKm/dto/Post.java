package com.qf.endOneKm.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data

@TableName("t_post")
public class Post {

    @TableId(type = IdType.AUTO)
    @TableField("post_id")
    private Long postId;
    @TableField("post_name")
    private String postName;
    @TableField("post_ads")
    private String postAds;
    @TableField("post_status")
    private Integer postStatus;
    @TableField("post_remarks")
    private String  postRemarks;
    @TableField("post_charge")
    private String postCharge;
    @TableField("post_charge_phone")
    private String postChargePhone;
    @TableField("transfer_id")
    private Integer transferId;
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("post_affiliation")
    private String postAffiliation;
    @TableField("post_charge_id")
    private String postChargeId;
    @TableField("transfer_name")
    private String transferName;


}
