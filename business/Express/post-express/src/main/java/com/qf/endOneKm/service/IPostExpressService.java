package com.qf.endOneKm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.endOneKm.dto.PostExpress;

import java.util.Map;

public interface IPostExpressService extends IService<PostExpress> {
    void updateById2(Map admin);
}
