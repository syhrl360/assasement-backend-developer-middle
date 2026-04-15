package com.middle.assessment.accountservice.mapper;

import com.middle.assessment.accountservice.dto.KafkaLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface KafkaLogMapper {

    @Insert("INSERT INTO kafka_log (service_receiver, topic_name, group_id, content) VALUES (#{serviceReceiver}, #{topicName}, #{groupId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(KafkaLog kafkaLog);
}
