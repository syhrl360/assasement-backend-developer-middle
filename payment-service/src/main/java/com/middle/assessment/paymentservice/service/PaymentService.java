package com.middle.assessment.paymentservice.service;

import com.middle.assessment.paymentservice.dto.PaymentRecord;
import com.middle.assessment.paymentservice.mapper.PaymentMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    public PaymentRecord findByUserId(Long userId) {
        return paymentMapper.findByUserId(userId);
    }

    public List<PaymentRecord> findAll(){
        return paymentMapper.findAll();
    }

    public void insert(PaymentRecord paymentRecord) {
        paymentMapper.insert(paymentRecord);
    }

    public void update(PaymentRecord paymentRecord) {
        paymentMapper.update(paymentRecord);
    }

    public void delete(Long id) {
        paymentMapper.delete(id);
    }
}
