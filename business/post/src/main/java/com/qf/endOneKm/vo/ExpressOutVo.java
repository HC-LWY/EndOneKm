package com.qf.endOneKm.vo;

import com.qf.endOneKm.dto.PostExpress;
import lombok.Data;


@Data
public class ExpressOutVo {


    public Long expressId;

    private Integer postId;

    private String siteName;

    private String outPhone;


}
