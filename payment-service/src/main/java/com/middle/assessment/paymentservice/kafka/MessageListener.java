package com.middle.assessment.paymentservice.kafka;

import com.middle.assessment.paymentservice.client.AccountServiceClient;
import com.middle.assessment.paymentservice.dto.KafkaLog;
import com.middle.assessment.paymentservice.dto.PaymentRecord;
import com.middle.assessment.paymentservice.dto.UserAccount;
import com.middle.assessment.paymentservice.mapper.KafkaLogMapper;
import com.middle.assessment.paymentservice.service.PaymentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Calendar;
import java.util.Date;

@Component
public class MessageListener {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    private final PaymentService paymentService;

    private final AccountServiceClient accountServiceClient;

    private final KafkaLogMapper kafkaLogMapper;

    public MessageListener(PaymentService paymentService,
                           AccountServiceClient accountServiceClient,
                           KafkaLogMapper kafkaLogMapper) {
        this.paymentService = paymentService;
        this.accountServiceClient = accountServiceClient;
        this.kafkaLogMapper = kafkaLogMapper;
    }

    @KafkaListener(topics = "${loan.topic.name}", groupId = "payment_group_id", containerFactory = "paymentKafkaListenerContainerFactory")
    public void loanRecordSuccessListener(String message) {
        logger.info("Received message in loanRecord kafka listener : {}", message);
        if (StringUtils.isNotBlank(message)) {
            try {

                KafkaLog kafkaLog = new KafkaLog();
                kafkaLog.setServiceReceiver("payment-service");
                kafkaLog.setTopicName("loan_topic");
                kafkaLog.setGroupId("payment_group_id");
                kafkaLog.setContent(message);
                kafkaLogMapper.insert(kafkaLog);

                JsonNode jsonNode = objectMapper.readTree(message);
                Long userId = jsonNode.get("userId").asLong();
                String orderId = jsonNode.get("orderId").asString();
                String name = jsonNode.get("name").asString();
                UserAccount userAccount = accountServiceClient.findByUserId(userId);
                PaymentRecord paymentRecord = new PaymentRecord();
                paymentRecord.setUserId(userId);
                paymentRecord.setOrderId(orderId);
                paymentRecord.setName(name);
                paymentRecord.setBankAccount(userAccount.getBankAccount());
                paymentRecord.setBankName(userAccount.getBankName());
                paymentRecord.setRepayAmount(0L);
                paymentRecord.setAdminFee(0L);
                paymentRecord.setDueDate(DateUtils.addDays(new Date(), 30));
                paymentService.insert(paymentRecord);
            } catch (Exception e) {
                logger.error("Error [Kafka - loanRecordSuccessListener] error exception ", e);
            }
        } else {
            logger.info("====> [Kafka - loanRecordSuccessListener] message is blank or null");
        }
    }

















}
