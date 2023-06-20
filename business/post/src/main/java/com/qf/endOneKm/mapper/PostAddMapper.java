package com.qf.endOneKm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.endOneKm.dto.PostExpress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface PostAddMapper extends BaseMapper<PostExpress> {


    String selectTransfer(Integer postId);

    Map selectExpress(@Param("expressId") Long expressId,@Param("transferName") String transferName);

    Integer updateStatus(@Param("expressId") Long expressId, @Param("siteName") String transferName,@Param("outTime")  Date date);

    PostExpress selectExpressStatus(@Param("expressId") Long expressId,@Param("postId") Integer postId);

    Integer updateOutStatus(@Param("expressId") Long expressId, @Param("postId") Integer postId);
}
