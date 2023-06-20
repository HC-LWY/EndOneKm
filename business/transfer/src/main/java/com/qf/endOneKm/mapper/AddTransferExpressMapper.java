package com.qf.endOneKm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.end.OneKm.dto.TransferExpress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface AddTransferExpressMapper extends BaseMapper<TransferExpress> {


    Map selectPostExpress(@Param("expressId") Long expressId,@Param("siteName") String transferName);

    Map selectTransferExpress(@Param("expressId") Long expressId, @Param("siteName") String transferName);

    String selectPost(String town);

    Integer updatePStatus(@Param("expressId") Long expressId, @Param("siteName")  String site_name,@Param("outTime") Date outTime);

    Integer updateTStatus(@Param("expressId") Long expressId,@Param("siteName")  String warehousing,@Param("outTime") Date outTime);

    String selectTransfer(String town);

    String selectTransferNameById(Integer transferId);
}
