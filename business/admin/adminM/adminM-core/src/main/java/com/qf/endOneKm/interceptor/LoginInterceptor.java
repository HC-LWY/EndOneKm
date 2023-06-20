package com.qf.endOneKm.interceptor;

import com.qf.endOneKm.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if(request.getMethod().equals("OPTIONS")){
            return true;
        }


        //获取请求头中的账号
        String parameter = request.getHeader("loginToken");

        if(parameter==null||parameter.equals("")){

            throw new BusinessException(201,"密钥为空");


        }
        //根据账号判断登录状态
        String s = redisTemplate.opsForValue().get("loginToken:"+parameter);



        if(s==null){
            throw new BusinessException(444,"登录状态过期");

        }






        //放行
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }



}
