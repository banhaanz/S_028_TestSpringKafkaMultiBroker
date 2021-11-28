package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
	@Value(value = "${spring.kafka.consumer.paotang-bootstrap-servers}")
    private String paotangBootstrapAddress;
	
	@Value(value = "${spring.kafka.consumer.tungnern-bootstrap-servers}")
    private String tungngernBootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value(value = "${spring.kafka.consumer.group-id-retry}")
    private String groupIdRetry;
    
    @Value("${spring.kafka.consumer.security.protocol}")
    private String securityProtocol;

    @Value("${spring.kafka.consumer.properties.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.kafka.consumer.properties.sasl.jaas.config-tn}")
    private String saslJaasConfigTn;

    @Value("${spring.kafka.consumer.properties.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.kafka.consumer.ssl.endpoint.identification.algorithm}")
    private String sslIdentificationAlgorithm;

    @Value("${spring.kafka.consumer.ssl.trust-store-location}")
    private String sslTrustStoreLocation;

    @Value("${spring.kafka.consumer.ssl.trust-store-password}")
    private String sslTrustStorePassword;

    @Value("${spring.kafka.consumer.ssl-tn.trust-store-location}")
    private String sslTrustStoreLocationTn;

    @Value("${spring.kafka.consumer.ssl-tn.trust-store-password}")
    private String sslTrustStorePasswordTn;

    @Value("${spring.kafka.consumer.ssl-tn.trust-store-type}")
    private String sslTrustStoreType;

    // 1. Consume string data from Kafka paotang

    @Bean
    public ConsumerFactory<String, String> paotangConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, paotangBootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put("security.protocol", securityProtocol);
        props.put("sasl.jaas.config", saslJaasConfig);
        props.put("sasl.mechanism", saslMechanism);
        props.put("ssl.endpoint.identification.algorithm", sslIdentificationAlgorithm);
        props.put("ssl.truststore.location", sslTrustStoreLocation);
        props.put("ssl.truststore.password", sslTrustStorePassword);
        props.put("ssl.truststore.type", sslTrustStoreType);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    ptkafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paotangConsumerFactory());
        return factory;
    }

    // 2. Consume user objects from Kafka tungnern

    @Bean
    public ConsumerFactory<String, String> tungngernConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, tungngernBootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdRetry);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put("security.protocol", securityProtocol);
        props.put("sasl.jaas.config", saslJaasConfigTn);
        props.put("sasl.mechanism", saslMechanism);
        props.put("ssl.truststore.location", sslTrustStoreLocationTn);
        props.put("ssl.truststore.password", sslTrustStorePasswordTn);
        props.put("ssl.truststore.type", sslTrustStoreType);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> tnKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(tungngernConsumerFactory());
        return factory;
    }

    // 3. Consume user objects from Kafka tungngern Internal
    @Value(value = "${spring.kafka.consumer.tungnern-internal-servers}")
    private String tungngernInternalAddress;
    @Value("${spring.kafka.consumer.properties.sasl.mechanism-tn-internal}")
    private String mechanismTnInternal;
    @Value("${spring.kafka.consumer.properties.sasl.jaas.config-tn-internal}")
    private String saslJaasConfigTnInternal;
    @Value("${spring.kafka.consumer.ssl-tn-internal.trust-store-location}")
    private String sslTrustStoreLocationTnInternal;
    @Value("${spring.kafka.consumer.ssl-tn-internal.trust-store-password}")
    private String sslTrustStorePasswordTnInternal;
    @Value("${spring.kafka.consumer.ssl-tn-internal.trust-store-type}")
    private String sslTrustStoreTypeInternal;
    @Value(value = "${spring.kafka.consumer.group-id-pep-ycyd}")
    private String groupIdPepYcyd;

    @Bean
    public ConsumerFactory<String, String> tungngernInternalFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, tungngernInternalAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdPepYcyd);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put("security.protocol", securityProtocol);
        props.put("sasl.jaas.config", saslJaasConfigTnInternal);
        props.put("sasl.mechanism", mechanismTnInternal);
        props.put("ssl.truststore.location", sslTrustStoreLocationTnInternal);
        props.put("ssl.truststore.password", sslTrustStorePasswordTnInternal);
        props.put("ssl.truststore.type", sslTrustStoreTypeInternal);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> tnInternalKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(tungngernInternalFactory());
        return factory;
    }
}
