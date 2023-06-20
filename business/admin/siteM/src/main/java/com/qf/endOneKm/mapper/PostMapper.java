package com.qf.endOneKm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.endOneKm.dto.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PostMapper extends BaseMapper<Post> {

    Integer delPost(Integer postId);

    List<Post> selectByAffiliation(@Param("postAffiliation") String postAffiliation);

    Map selectAccount(String postCharge);

    String selectCurrentAdmin(Long postId);

    Integer selectStatus(String userName);
}
