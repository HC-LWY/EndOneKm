package com.qf.end.OneKm.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("t_transfer_express")
@Data
public class TransferExpress extends Express {

    private Integer transferId;

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

    private Integer warehousingType;


}
