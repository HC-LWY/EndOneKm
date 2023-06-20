package com.qf.endOneKm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.endOneKm.dto.PostExpress;
import com.qf.endOneKm.mapper.PostExpressMapper;
import com.qf.endOneKm.service.IPostExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PostExpressServiceImpl extends ServiceImpl<PostExpressMapper, PostExpress> implements IPostExpressService {


    @Autowired
    private PostExpressMapper postExpressMapper;

    @Override
    public void updateById2(Map admin) {
        postExpressMapper.update2((String)admin.get("id"),(String)admin.get("name"));
    }
}
