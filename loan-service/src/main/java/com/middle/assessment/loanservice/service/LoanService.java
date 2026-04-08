package com.middle.assessment.loanservice.service;

import com.middle.assessment.loanservice.client.PaymentServiceClient;
import com.middle.assessment.loanservice.dto.LoanRecord;
import com.middle.assessment.loanservice.dto.PaymentRecord;
import com.middle.assessment.loanservice.mapper.LoanMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoanService {
    private final LoanMapper loanMapper;

    private PaymentServiceClient paymentServiceClient;

    public LoanService(LoanMapper loanMapper,
                       PaymentServiceClient paymentServiceClient){
        this.loanMapper = loanMapper;
        this.paymentServiceClient = paymentServiceClient;
    }

    public LoanRecord findByUserId(Long userId) {
        return loanMapper.findByUserId(userId);
    }

    public List<LoanRecord> findAll() {
        return loanMapper.findAll();
    }

    public void insert(LoanRecord loanRecord) {
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setUserId(loanRecord.getUserId());
        paymentRecord.setOrderId(loanRecord.getOrderId());
        paymentRecord.setName(loanRecord.getName());
        paymentRecord.setBankAccount("222299900");
        paymentRecord.setBankName("BCA");
        paymentRecord.setRepayAmount(loanRecord.getLoanAmount()+5000);
        paymentRecord.setAdminFee(5000);
        paymentServiceClient.insert(paymentRecord);
        loanMapper.insert(loanRecord);
    }

    public void update(LoanRecord loanRecord) {
        LoanRecord existingLoanRecord = findByUserId(loanRecord.getUserId());
        loanRecord.setId(existingLoanRecord.getId());
        loanRecord.setUserId(existingLoanRecord.getUserId());
        loanRecord.setOrderId(loanRecord.getOrderId()!=null?loanRecord.getOrderId():existingLoanRecord.getOrderId());
        loanRecord.setName(loanRecord.getName()!=null?loanRecord.getName():existingLoanRecord.getName());
        loanRecord.setLoanAmount(loanRecord.getLoanAmount()!=null?loanRecord.getLoanAmount():existingLoanRecord.getLoanAmount());
        loanRecord.setLoanDate(loanRecord.getLoanDate()!=null?loanRecord.getLoanDate():existingLoanRecord.getLoanDate());
        loanRecord.setDescription(loanRecord.getDescription()!=null?loanRecord.getDescription():existingLoanRecord.getDescription());
        loanMapper.update(loanRecord);
    }

    public void delete(Long id) {
        loanMapper.delete(id);
    }


}
