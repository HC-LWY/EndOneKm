package com.qf.endOneKm.controller;

import com.alibaba.fastjson.JSON;
import com.qf.endOneKm.R;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.service.IOssService;
import com.qf.endOneKm.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private IOssService ossService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public R login(String account,String password){
        //1.校验数据合法性
        Admin admin=ossService.getAdminByAccount(account);
        if(admin==null){
            throw new BusinessException(500,"该用户不存在");
        }else if(!admin.getJurisdiction().equals("超级管理员")){
            throw new BusinessException(500,"登录权限不够");
        }else if(!admin.getPassword().equals(password)){
            throw new BusinessException(500,"密码错误");
        }

        //2.执行登录操作，响应Token
        String token= JSON.toJSONString(admin);
        //3.redis保存token，并设置过期时间

        String refToken=getUUID();
        redisTemplate.opsForValue().set("loginToken:"+account,token,1800, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("refToken:"+refToken,account,3600, TimeUnit.SECONDS);
        Long expirationTime = getExpirationTime();

        return R.ok().put("msg","登录成功").put("loginToken",token).put("refreshToken", refToken).put("expirationTime",expirationTime);
    }

    @PostMapping("/refToken")
    public R refToken(String loginToken, String refToken){

        if(refToken==null){
            throw new BusinessException(250,"刷新密钥为空");
        }

        String account = redisTemplate.opsForValue().get("refToken:"+refToken);

        if(account==null){
            throw new BusinessException(250,"登录状态过期");
        }


        if(!account.equals(loginToken)){
            throw new BusinessException(250,"刷新token不合法");
        }
        Admin admin=ossService.getAdminByAccount(account);
        String token= JSON.toJSONString(admin);
        String refToken2=getUUID();
        redisTemplate.delete("refToken:"+refToken);
        redisTemplate.opsForValue().set("loginToken:"+account,token,1800, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("refToken:"+refToken2,account,3600, TimeUnit.SECONDS);
        Long expirationTime = getExpirationTime();
        return R.ok().put("msg","token已刷新").put("loginToken",token).put("refreshToken", refToken2).put("expirationTime",expirationTime);
    }

    public String getUUID(){
        return UUID.randomUUID().toString();
    }

    public Long getExpirationTime(){

        return new Date().getTime()+1800000;

    }



}
