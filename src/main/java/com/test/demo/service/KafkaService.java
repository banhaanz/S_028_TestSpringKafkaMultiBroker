package com.test.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaService {

    @Value("${spring.kafka.producer.topic.topic-notification}")
    private String topicNotification;

    @Value("${spring.kafka.producer.topic.topic-retry}")
    private String topicRetry;

    @Value("${spring.kafka.producer.topic.topic-pay-result}")
    private String topicPayResult;

    private final ObjectMapper objectMapper;
    
    private RoutingKafkaTemplate routingKafkaTemplate;

    public KafkaService(RoutingKafkaTemplate routingKafkaTemplate,
                        ObjectMapper objectMapper) {
        this.routingKafkaTemplate = routingKafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<SendResult<Object, Object>> sendKafkaTopicRetry(String data) {
        return routingKafkaTemplate.send(topicRetry, data).completable();
    }

    public CompletableFuture<SendResult<Object, Object>> sendKafkaNotification(String data) {
        return routingKafkaTemplate.send(topicNotification, data).completable();
    }

    public CompletableFuture<SendResult<Object, Object>> sendToKafkaPayResult(String data) {
        return routingKafkaTemplate.send(topicPayResult, data).completable();
    }
}
