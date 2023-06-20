package com.qf.endOneKm.controller;


import com.qf.endOneKm.R;
import com.qf.endOneKm.dto.Post;
import com.qf.endOneKm.exception.BusinessException;
import com.qf.endOneKm.service.IPostService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/list")
    public R list(){
        //1.查询所有中转站信息
        List<Post> list = postService.list();

        //2.防止返回空集合到前端
        if(list.size()==0||list==null){
            throw new BusinessException(500,"没有驿站数据");
        }

        return R.ok().put("data",list);
    }




    @PostMapping("/addPost")
    public R addPost(Post post){
        //1.校验数据合法性
        post.setCreateTime(new Date());
        List posts=postService.getPostByAffiliation(post.getPostAffiliation());
        if(post!=null||posts.size()>0){
            throw new BusinessException(500,"该地区已有驿站了");
        }


        //2.执行驿站注册

        boolean save = postService.save(post);

        if(!save){
            throw new BusinessException(500,"驿站注册失败");
        }

        //3.响应前端


        return R.ok().put("msg","驿站注册成功");
    }





    @PutMapping("/putPost")
    public R putPost(Post post){
        //1.校验数据合法性
        String postCharge = post.getPostCharge();
        Map map1=postService.getAccountByName(postCharge);

        if(postCharge!=null||!postCharge.equals("")){
            if(map1==null){
                throw new BusinessException(500,"没有该管理员");
            }
            post.setPostChargeId((String) map1.get("account_name"));
        }

        if(((Integer)map1.get("user_status"))!=0&&!((String)map1.get("site_name")).equals(post.getPostName())){
            throw new BusinessException(500,"所修改的管理员还没有离职");
        }

        boolean b1 = postService.currentStatus(post.getPostId(),(String)map1.get("user_name"));

        if(b1){
            throw new BusinessException(500,"当前本站管理员还未离职");
        }



        //2.修改驿站信息
        boolean b = postService.updateById(post);
        if(!b){
            throw new BusinessException(500,"修改驿站信息失败");
        }



        //3.通知后台修改相关管理员信息
        Map map=new HashMap();
        map.put("chargeId",post.getPostChargeId());
        map.put("siteName",post.getPostName());
        map.put("userStatus",1);
        if(((String)map1.get("jurisdiction")).equals("超级管理员")){
            map.put("jurisdiction","超级管理员");
        }else {
            map.put("jurisdiction", "驿站");
        }


        rabbitTemplate.convertAndSend("myAdminExchange","/put",map);


        //4.响应前端
        if (!b) {
            throw new BusinessException(500,"驿站信息修改失败");
        }



        return R.ok().put("msg","驿站信息修改成功");
    }



    @DeleteMapping("/{postId}")
    public R delPost(@PathVariable Integer postId){
        //1.检查该驿站的状态
        Post post = postService.getById(postId);

        if(post==null){
            throw new BusinessException(500,"没有该驿站信息");
        }else if(post.getPostStatus()==0){
            throw new BusinessException(500,"该驿站已被注销");
        }

        //2.注销站点信息
        boolean b=postService.delPost(postId);
        if(!b){
            throw new BusinessException(500,"驿站注销失败");
        }


        //3.通知后台有驿站注销，修改管理员信息
        rabbitTemplate.convertAndSend("myAdminExchange","/del",post.getPostChargeId());



        //4.响应前端



        return R.ok().put("msg","驿站信息注销成功");
    }

}
