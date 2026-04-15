package com.middle.assessment.accountservice.kafka;

import com.middle.assessment.accountservice.dto.KafkaLog;
import com.middle.assessment.accountservice.dto.UserAccount;
import com.middle.assessment.accountservice.mapper.KafkaLogMapper;
import com.middle.assessment.accountservice.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class MessageListener {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    private final AccountService accountService;

    private final KafkaLogMapper kafkaLogMapper;

    public MessageListener(AccountService accountService,
                           KafkaLogMapper kafkaLogMapper) {
        this.accountService = accountService;
        this.kafkaLogMapper = kafkaLogMapper;
    }

    @KafkaListener(topics = "${loan.topic.name}", groupId = "account_group_id", containerFactory = "accountKafkaListenerContainerFactory")
    public void loanRecordSuccessListener(String message) {
        logger.info("Received message in loanRecord kafka listener : {}", message);
        if(StringUtils.isNotBlank(message)){
            try {

                KafkaLog kafkaLog = new KafkaLog();
                kafkaLog.setServiceReceiver("account-service");
                kafkaLog.setTopicName("loan_topic");
                kafkaLog.setGroupId("account_group_id");
                kafkaLog.setContent(message);
                kafkaLogMapper.insert(kafkaLog);

                JsonNode jsonNode = objectMapper.readTree(message);
                Long userId = jsonNode.get("userId").asLong();
                Long loanAmount = jsonNode.get("loanAmount").asLong();
                UserAccount userAccount = accountService.findByUserId(userId);
                userAccount.setBalance(userAccount.getBalance()+loanAmount);
                accountService.update(userAccount);
            } catch (Exception e) {
                logger.error("Error [Kafka - loanRecordSuccessListener] error exception ", e);
            }
        } else {
            logger.info("====> [Kafka - loanRecordSuccessListener] message is blank or null");
        }
    }
}
