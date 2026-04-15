package com.middle.assessment.loanservice.service;

import com.middle.assessment.loanservice.client.AccountServiceClient;
import com.middle.assessment.loanservice.client.PaymentServiceClient;
import com.middle.assessment.loanservice.dto.LoanRecord;
import com.middle.assessment.loanservice.dto.PaymentRecord;
import com.middle.assessment.loanservice.dto.UserAccount;
import com.middle.assessment.loanservice.kafka.MessageProducer;
import com.middle.assessment.loanservice.kafka.service.KafkaService;
import com.middle.assessment.loanservice.mapper.LoanMapper;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class LoanService {


    private final ObjectMapper objectMapper = new ObjectMapper();

    private final LoanMapper loanMapper;
    private final PaymentServiceClient paymentServiceClient;
    private final AccountServiceClient accountServiceClient;

    private final KafkaService kafkaService;


    public LoanService(LoanMapper loanMapper,
                       PaymentServiceClient paymentServiceClient,
                       AccountServiceClient accountServiceClient,
                       KafkaService kafkaService){
        this.loanMapper = loanMapper;
        this.paymentServiceClient = paymentServiceClient;
        this.accountServiceClient = accountServiceClient;
        this.kafkaService = kafkaService;
    }

    public LoanRecord findByUserId(Long userId) {
        return loanMapper.findByUserId(userId);
    }

    public List<LoanRecord> findAll() {
        return loanMapper.findAll();
    }

    public void insert(LoanRecord loanRecord) {
        UserAccount userAccount = accountServiceClient.findByUserId(loanRecord.getUserId());
        if (userAccount != null) {
            loanMapper.insert(loanRecord);
            String loanRecordJSONString = objectMapper.writeValueAsString(loanRecord);
            kafkaService.sendMessage(loanRecordJSONString);
        }

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
