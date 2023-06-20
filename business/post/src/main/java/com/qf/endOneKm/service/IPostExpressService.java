package com.qf.endOneKm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.endOneKm.dto.PostExpress;
import com.qf.endOneKm.vo.ExpressOutVo;

public interface IPostExpressService extends IService<PostExpress> {
    Long addPostExpress(PostExpress postExpress);

    boolean addPostExpress2(PostExpress postExpress);

    boolean outPostExpress(ExpressOutVo expressOutVo);
}
