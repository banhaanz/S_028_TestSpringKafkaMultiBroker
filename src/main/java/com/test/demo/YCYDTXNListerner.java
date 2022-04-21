package com.test.demo;

import com.test.demo.log.ListenerLogger;
import com.test.demo.service.PEPYCYDService;
import com.test.demo.service.YCYDService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class YCYDTXNListerner {

    private static final ListenerLogger listenerLogger = new ListenerLogger(YCYDTXNListerner.class);
    private final Logger logger = LogManager.getLogger(YCYDTXNListerner.class);
    private final YCYDService ycydService;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.consumer.topic.topic-ycyd-payment-result}")
    private String topicYCYDTXN;
    @Value("${spring.kafka.consumer.topic.topic-pep-ycyd}")
    private String topicPEPYCYD;

    @Value("${spring.kafka.consumer.topic.topic-retry}")
    private String topicRetry;

    @Autowired
    private PEPYCYDService pepycydService;

    public YCYDTXNListerner(YCYDService ycydService) {
        this.ycydService = ycydService;
    }

    @Async
    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic.topic-ycyd-payment-result}'}",
    		containerFactory = "ptkafkaListenerContainerFactory")
    public void YCYDTXNListerner(String data) throws Exception {
        initLog(topicYCYDTXN, "YCYD Txn Listener");
        logger.info("Message: {}", data);
        ycydService.ycydtxn(data);
    }

    @Async
    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic.topic-retry}'}",
    		containerFactory = "tnKafkaListenerContainerFactory")
    public void YCYDTXNRetry(String data) throws Exception {
        initLog(topicRetry, "YCYD TXN Retry");
        logger.info("Message: {}", data);
        ycydService.saveFromTopicRetry(data);
    }

    @Async
    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic.topic-pep-ycyd}'}", containerFactory = "tnInternalKafkaListenerContainerFactory")
    public void PepYCYDListener(String data) throws Exception {
        initLog(topicPEPYCYD, "PEP-YCYD Txn Listener");
        logger.info("Tn Pep Message: {}", data);
        pepycydService.pepYcydTxn(data);
    }

    public void initLog(String topicName, String serviceName) {
        listenerLogger.updateContextMap(ListenerLogger.SERVICE_NAME, serviceName);
        listenerLogger.updateContextMap(ListenerLogger.TOPIC_NAME, topicName);
        listenerLogger.updateContextMap(ListenerLogger.CONSUMER_GROUP, groupId);
    }
}
