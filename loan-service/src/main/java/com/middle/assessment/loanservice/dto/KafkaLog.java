package com.middle.assessment.loanservice.dto;

public class KafkaLog {

    private Long id;

    private String serviceReceiver;

    private String topicName;

    private String groupId;

    private String content;


    public KafkaLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceReceiver() {
        return serviceReceiver;
    }

    public void setServiceReceiver(String serviceReceiver) {
        this.serviceReceiver = serviceReceiver;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
