package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String tungngernBrokers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String tungngernKeySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String tungngernValueSerializer;

    @Value("${spring.kafka.producer.security.protocol}")
    private String securityProtocol;

    @Value("${spring.kafka.producer.properties.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.kafka.producer.properties.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.kafka.producer.ssl.trust-store-location}")
    private String sslTrustStoreLocation;

    @Value("${spring.kafka.producer.ssl.trust-store-password}")
    private String sslTrustStorePassword;

    @Value("${spring.kafka.producer.ssl.trust-store-type}")
    private String sslTrustStoreType;

    @Value("${spring.kafka.producer.topic.topic-retry}")
    private String topicRetry;

    @Value("${spring.kafka.producer.topic.topic-notification}")
    private String topicNotification;

    @Value("${spring.kafka.producer.topic.topic-pay-result}")
    private String topicPayResult;


    // 3. Consume user objects from Kafka tungngern Internal
    @Value(value = "${spring.kafka.producer.tungnern-internal-servers}")
    private String tungngernInternalAddress;
    @Value("${spring.kafka.producer.properties.sasl.mechanism-tn-internal}")
    private String mechanismTnInternal;
    @Value("${spring.kafka.producer.properties.sasl.jaas.config-tn-internal}")
    private String saslJaasConfigTnInternal;
    @Value("${spring.kafka.producer.ssl-tn-internal.trust-store-location}")
    private String sslTrustStoreLocationTnInternal;
    @Value("${spring.kafka.producer.ssl-tn-internal.trust-store-password}")
    private String sslTrustStorePasswordTnInternal;
    @Value("${spring.kafka.producer.ssl-tn-internal.trust-store-type}")
    private String sslTrustStoreTypeInternal;

    @Bean
    public RoutingKafkaTemplate routingTemplate(GenericApplicationContext context) {
        Map<Pattern, ProducerFactory<Object, Object>> map = new LinkedHashMap<>();
        Map<String, Object> tungngernProps = new HashMap<>();
        tungngernProps = new HashMap<>();
        tungngernProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, tungngernBrokers);
        tungngernProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, tungngernKeySerializer);
        tungngernProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, tungngernValueSerializer);
        tungngernProps.put("security.protocol", securityProtocol);
        tungngernProps.put("sasl.jaas.config", saslJaasConfig);
        tungngernProps.put("sasl.mechanism", saslMechanism);
        tungngernProps.put("ssl.truststore.location", sslTrustStoreLocation);
        tungngernProps.put("ssl.truststore.password", sslTrustStorePassword);
        tungngernProps.put("ssl.truststore.type", sslTrustStoreType);
        DefaultKafkaProducerFactory<Object, Object> tungngernKafkaProducerFactory = new DefaultKafkaProducerFactory<>(tungngernProps);
        context.registerBean(DefaultKafkaProducerFactory.class, "tungngernKafkaProducerFactory", tungngernKafkaProducerFactory);

        Map<String, Object> tungngernInternalProps = new HashMap<>();
        tungngernInternalProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, tungngernInternalAddress);
        tungngernInternalProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, tungngernKeySerializer);
        tungngernInternalProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, tungngernValueSerializer);
        tungngernInternalProps.put("security.protocol", securityProtocol);
        tungngernInternalProps.put("sasl.jaas.config", saslJaasConfigTnInternal);
        tungngernInternalProps.put("sasl.mechanism", mechanismTnInternal);
        tungngernInternalProps.put("ssl.truststore.location", sslTrustStoreLocationTnInternal);
        tungngernInternalProps.put("ssl.truststore.password", sslTrustStorePasswordTnInternal);
        tungngernInternalProps.put("ssl.truststore.type", sslTrustStoreTypeInternal);
        DefaultKafkaProducerFactory<Object, Object> tungngernKafkaInternalProducerFactory = new DefaultKafkaProducerFactory<>(tungngernInternalProps);
        
        map.put(Pattern.compile(topicRetry), tungngernKafkaProducerFactory);
        map.put(Pattern.compile(topicNotification), tungngernKafkaInternalProducerFactory);
        map.put(Pattern.compile(topicPayResult), tungngernKafkaProducerFactory);
        return new RoutingKafkaTemplate(map);
    }
}
