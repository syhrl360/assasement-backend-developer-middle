package com.middle.assessment.loanservice.kafka.service;

import com.middle.assessment.loanservice.kafka.MessageProducer;
import org.springframework.stereotype.Service;

@Service("kafkaService")
public class KafkaServiceImpl implements KafkaService{

    private final MessageProducer messageProducer;

    public KafkaServiceImpl(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }


    @Override
    public void sendMessage(String message) {
        messageProducer.sendLoanMessage(message);
    }
}
