package com.qf.endOneKm.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


@TableName("t_post_express")
@Data
public class PostExpress extends Express {

    private Integer postId;

    private String siteName;

    private Integer expressStatus;

    private String warehousing;

    private String outbound;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date warehousingTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date outboundTime;


}
