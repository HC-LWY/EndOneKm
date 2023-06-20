package com.qf.endOneKm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.endOneKm.dto.PostExpress;
import org.apache.ibatis.annotations.Param;

public interface PostExpressMapper extends BaseMapper<PostExpress> {


    void update2(@Param("id") String id, @Param("name") String name);
}
