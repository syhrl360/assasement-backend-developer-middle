package com.middle.assessment.accountservice.service;

import com.middle.assessment.accountservice.dto.UserAccount;
import com.middle.assessment.accountservice.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountMapper accountMapper;

    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }


    public UserAccount findByUserId(Long userId) {
        return accountMapper.findByUserId(userId);
    }

    public List<UserAccount> findAll(){
        return accountMapper.findAll();
    }

    public void insert(UserAccount paymentRecord) {
        accountMapper.insert(paymentRecord);
    }

    public void update(UserAccount paymentRecord) {
        accountMapper.update(paymentRecord);
    }

    public void delete(Long id) {
        accountMapper.delete(id);
    }
}
