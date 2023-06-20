package com.qf.endOneKm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.end.OneKm.dto.TransferExpress;
import com.qf.endOneKm.mapper.TransferExpressMapper;
import com.qf.endOneKm.service.IPostExpressService;
import com.qf.endOneKm.service.ITransferExpressService;
import org.springframework.stereotype.Service;

@Service
public class TransferExpressServiceImpl extends ServiceImpl<TransferExpressMapper, TransferExpress> implements ITransferExpressService {
}
