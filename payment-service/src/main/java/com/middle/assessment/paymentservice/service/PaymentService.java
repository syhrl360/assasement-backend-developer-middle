package com.middle.assessment.paymentservice.service;

import com.middle.assessment.paymentservice.client.AccountServiceClient;
import com.middle.assessment.paymentservice.dto.PaymentRecord;
import com.middle.assessment.paymentservice.dto.UserAccount;
import com.middle.assessment.paymentservice.mapper.PaymentMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    private final AccountServiceClient accountServiceClient;

    public PaymentService(PaymentMapper paymentMapper,
                          AccountServiceClient accountServiceClient) {
        this.paymentMapper = paymentMapper;
        this.accountServiceClient = accountServiceClient;
    }

    public PaymentRecord findByUserId(Long userId) {
        return paymentMapper.findByUserId(userId);
    }

    public List<PaymentRecord> findAll(){
        return paymentMapper.findAll();
    }

    public void insert(PaymentRecord paymentRecord) {
        UserAccount userAccount = new UserAccount();
        userAccount = accountServiceClient.findByUserId(paymentRecord.getUserId());
        userAccount.setBalance(userAccount.getBalance() + paymentRecord.getRepayAmount() - 5000);
        accountServiceClient.update(userAccount.getId(), userAccount);
        
        paymentMapper.insert(paymentRecord);
    }

    public void update(PaymentRecord paymentRecord) {
        paymentMapper.update(paymentRecord);
    }

    public void delete(Long id) {
        paymentMapper.delete(id);
    }
}
