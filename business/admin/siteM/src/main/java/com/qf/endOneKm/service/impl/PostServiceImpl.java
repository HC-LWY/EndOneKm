package com.qf.endOneKm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.endOneKm.dto.Post;
import com.qf.endOneKm.mapper.PostMapper;
import com.qf.endOneKm.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Autowired
    private PostMapper postMapper;


    @Override
    public boolean delPost(Integer postId) {

        Integer i=postMapper.delPost(postId);


        return i>0;
    }

    @Override
    public List getPostByAffiliation(String postAffiliation) {

        return postMapper.selectByAffiliation(postAffiliation);
    }

    @Override
    public Map getAccountByName(String postCharge) {
        return postMapper.selectAccount(postCharge);
    }

    @Override
    public boolean currentStatus(Long postId, String userName) {
        String s = postMapper.selectCurrentAdmin(postId);
        if(s.equals(userName)){
            return false;
        }
        return postMapper.selectStatus(s)>0;
    }
}
