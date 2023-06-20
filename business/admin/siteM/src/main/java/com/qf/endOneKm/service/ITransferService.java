package com.qf.endOneKm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.endOneKm.dto.Transfer;
import java.util.List;
import java.util.Map;


public interface ITransferService extends IService<Transfer> {
    boolean delTransfer(Integer transferId);

    List getTransferByTown(String town);

    Map getAccountByName(String transferCharge);

    boolean currentStatus(Long transferId,String userName);
}
