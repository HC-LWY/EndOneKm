package com.qf.endOneKm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.endOneKm.mapper.TransferMapper;
import com.qf.endOneKm.service.ITransferService;
import com.qf.endOneKm.dto.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransferServiceImpl extends ServiceImpl<TransferMapper, Transfer> implements ITransferService {

    @Autowired
    private TransferMapper transferMapper;


    @Override
    public boolean delTransfer(Integer transferId) {
        //1.查看中转站快递信息，如果有待出库快递则不允许注销


        //2.修改中转站状态 --->0
        Integer i=transferMapper.delTransfer(transferId);

        //3.通知驿站修改绑定的中转站


        return i>0;
    }

    @Override
    public List getTransferByTown(String town) {

        List transfers=transferMapper.selectByTown(town);

        return transfers;
    }

    @Override
    public Map getAccountByName(String transferCharge) {


        return transferMapper.selectAccount(transferCharge);
    }

    @Override
    public boolean currentStatus(Long transferId,String userName) {
        String s = transferMapper.selectCurrentAdmin(transferId);
        if(s.equals(userName)){
            return false;
        }
        return transferMapper.selectStatus(s)>0;
    }
}
