package com.qf.endOneKm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.endOneKm.dto.Transfer;

import java.util.List;
import java.util.Map;

public interface TransferMapper extends BaseMapper<Transfer> {

    Integer delTransfer(Integer transferId);

    List<Map> selectByTown(String town);

    Map selectAccount(String transferCharge);

    String selectCurrentAdmin(Long transferId);

    Integer selectStatus(String userName);
}
