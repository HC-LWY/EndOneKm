package com.qf.endOneKm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.end.OneKm.dto.TransferExpress;

public interface IAddTransferExpressService extends IService<TransferExpress> {
    boolean addTransferExpress(TransferExpress transferExpress);
}
