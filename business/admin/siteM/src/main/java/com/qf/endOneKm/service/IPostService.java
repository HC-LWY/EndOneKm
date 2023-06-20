package com.qf.endOneKm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.endOneKm.dto.Post;

import java.util.List;
import java.util.Map;

public interface IPostService extends IService<Post> {
    boolean delPost(Integer postId);

    List getPostByAffiliation(String postAffiliation);

    Map getAccountByName(String postCharge);

    boolean currentStatus(Long postId, String user_name);
}
