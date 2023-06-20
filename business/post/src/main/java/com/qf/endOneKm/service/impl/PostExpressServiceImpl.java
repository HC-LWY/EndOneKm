package com.qf.endOneKm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.endOneKm.dto.PostExpress;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.mapper.PostAddMapper;
import com.qf.endOneKm.service.IPostExpressService;
import com.qf.endOneKm.vo.ExpressOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class PostExpressServiceImpl extends ServiceImpl<PostAddMapper, PostExpress> implements IPostExpressService {

    @Autowired
    private PostAddMapper postExpressMapper;

    @Override
    public Long addPostExpress(PostExpress postExpress) {
        Long expressId=getId();
        postExpress.setExpressId(expressId);
        postExpress.setOutbound(getTransfer(postExpress.getPostId()));
        postExpress.setWarehousingTime(new Date());
        baseMapper.insert(postExpress);

        return expressId;
    }

    @Override
    public boolean addPostExpress2(PostExpress postExpress) {
        //获取当前驿站绑定的中转站
        String transferName=getTransfer(postExpress.getPostId());
        //判断是否能入库
        Map map = ow(postExpress.getExpressId(), transferName);
        if(map==null){
            throw new BusinessException(400,"该快递不该出现在该地区");
        }
        //入库准备
        Integer i=postExpressMapper.updateStatus(postExpress.getExpressId(),transferName,new Date());

        postExpress.setAddress((String) map.get("address"));
        postExpress.setPhone((String) map.get("phone"));
        postExpress.setSender((String) map.get("sender"));
        postExpress.setStartAddress((String) map.get("start_address"));
        postExpress.setPostType((String) map.get("post_type"));
        postExpress.setWarehousingTime(new Date());
        postExpress.setWarehousing(transferName);
        postExpress.setOutbound("用户");

        if(!(i>0)){
            throw new BusinessException(400,"入库失败");
        }
        return baseMapper.insert(postExpress)>0;
    }

    @Override
    public boolean outPostExpress(ExpressOutVo expressOutVo) {

        //判断快递在驿站的状态
        PostExpress postExpress=postExpressMapper.selectExpressStatus(expressOutVo.getExpressId(),expressOutVo.getPostId());

        if(postExpress==null){
            throw new BusinessException(500,"没有该包裹信息,试着检查快递单号是否输错");
        }
        if(postExpress.getExpressStatus()==0){
            if (postExpress.getPhone().equals(expressOutVo.getOutPhone())) {
                Integer i =postExpressMapper.updateOutStatus(expressOutVo.getExpressId(),expressOutVo.getPostId());

                return true;
            }
        }else {
            throw new BusinessException(500,"该包裹已被取走");
        }

        return false;
    }


    public  Long getId() {
        long id=UUID.randomUUID().toString().hashCode();
        if(id<0){
            id=0-id;
        }
        String format = String.format("%010d", id).substring(0,10);
        return  Long.valueOf(format);
    }

//    public String getOutbound(String outTown){
//        int index = outTown.indexOf("镇");
//        String town = outTown.substring(0,index+1);
//        return postExpressMapper.selectTransfer(town);
//    }

    public String getTransfer(Integer postId){
        return postExpressMapper.selectTransfer(postId);
    }

    public Map ow(Long expressId,String transferName){
        Map map=postExpressMapper.selectExpress(expressId,transferName);
        return map;
    }
}
