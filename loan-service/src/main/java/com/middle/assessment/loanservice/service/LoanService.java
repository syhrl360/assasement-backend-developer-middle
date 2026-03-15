package com.middle.assessment.loanservice.service;

import com.middle.assessment.loanservice.dto.LoanRecord;
import com.middle.assessment.loanservice.mapper.LoanMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoanService {
    private final LoanMapper loanMapper;

    public LoanService(LoanMapper loanMapper){
        this.loanMapper = loanMapper;
    }

    public LoanRecord findByUserId(Long userId) {
        return loanMapper.findByUserId(userId);
    }

    public List<LoanRecord> findAll() {
        return loanMapper.findAll();
    }

    public void insert(LoanRecord loanRecord) {
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
