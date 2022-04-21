package com.test.demo.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.model.kafka.HeaderReq;
import com.test.demo.model.kafka.PayResult;
import com.test.demo.model.kafka.PayResultContent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Slf4j
public class MessageSender {

    public static void main(String[] args) {


        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "msg.cmmnent.dev.gcp.ktbcloud:19092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"msg_tungngern\" password=\"tungngern1234\";");
        props.put("sasl.mechanism", "PLAIN");
        props.put("ssl.truststore.location", "/Users/purit/Desktop/thungngern/tn-halfhalf-listener-txn/helm/tungngern.jks");
        props.put("ssl.truststore.password", "P@00word");
        props.put("ssl.truststore.type", "JKS");
        props.put("ssl.endpoint.identification.algorithm", "");

        List<String> a = Arrays.asList("paotang.ycyd.fct.pay-success.0");



        ObjectMapper mapper = new ObjectMapper();
        try (Producer<String,String> producer = new KafkaProducer<>(props)) {
            System.out.println("Hi");
            String messageId = "1234235145145";

            HeaderReq headerReq = new HeaderReq();
            headerReq.setChannelId("HALF_EACH");
            headerReq.setReqId("c97c931c-ade8-455e-8cbd-a77bf627add6");
            headerReq.setService("PayResult.sit");
            PayResultContent payResultContent = new PayResultContent();
            payResultContent.setTxnRefID("02478f99e6ab4c28b2764cc06a4175e6-qr");
            payResultContent.setMerchantId("20210722030002");
            payResultContent.setMerchantChannelId("TN");
            payResultContent.setMerchantName("ไอซ์น้ำปั่น");
            payResultContent.setMerchantAccNum("0722030002");
            payResultContent.setCompanyCode("99N030002");
            payResultContent.setMerchantType("10001");
            payResultContent.setWalletId("****");
            payResultContent.setWalletType("HALF_EACH");


            PayResult payResult = new PayResult();
            payResult.setContent(payResultContent);
            payResult.setHeaderReq(headerReq);

            String sampleMessage = "{\"qrRefId\":\"c9f7688c-22eb-481d-b90a-c996ef92c8a0\",\"txnRefId\":\"c9f7688c-22eb-481d-b90a-c996ef92c8a0\",\"merchantId\":\"P20210920110945669we\",\"walletType\":\"FOOD_DELIVERY\",\"amount\":\"2400\",\"txnDtm\":\"1632136050867\",\"paymentNo\":\"2040\",\"payerName\":\"ธวัลรัตน์ วงศ์โพธิพันธ์\",\"channelName\":\"PT\",\"branchId\":\"0000\",\"merchantLatitude\":null,\"merchantLongitude\":null,\"companyCode\":\"13B0660364\",\"settleType\":\"YCYD_PEP\",\"gwalletAmount\":\"2400\",\"evoucherAmount\":\"0\",\"payerId\":\"0814412277\",\"deviceUuid\":null,\"citizenId\":\"1909800902040\"}" ;

//            String sampleMessage = "{\n" +
//                    "    \"headerReq\": {\n" +
//                    "        \"channelId\": \"\",\n" +
//                    "        \"reqId\": \"\",\n" +
//                    "        \"service\": \"\"\n" +
//                    "    },\n" +
//                    "    \"content\": {\n" +
//                    "        \"merchantId\": \"\",\n" +
//                    "        \"merchantChannelId\": \"\",\n" +
//                    "        \"merchantName\": \"\",\n" +
//                    "        \"merchantAccNum\": \"\",\n" +
//                    "        \"companyCode\": \"\",\n" +
//                    "        \"merchantType\": \"\",\n" +
//                    "        \"txnRefID\": \"aabccc\",\n" +
//                    "        \"merchantTypeDesc\": \"\",\n" +
//                    "        \"walletChannelId\": \"\",\n" +
//                    "        \"walletId\": \"\",\n" +
//                    "        \"walletType\": \"SPEND_TO_GET\",\n" +
//                    "        \"walletRefId\": \"\",\n" +
//                    "        \"payerId\": \"1101122223\",\n" +
//                    "        \"payerName\": \"\",\n" +
//                    "        \"paymentType\": \"\",\n" +
//                    "        \"paymentAmount\": 1001.1,\n" +
//                    "        \"verifyType\": \"\",\n" +
//                    "        \"statusCode\": \"0000\",\n" +
//                    "        \"statusDesc\": \"\",\n" +
//                    "        \"qrCreateTimestamp\": 1499070300000,\n" +
//                    "        \"processTimestamp\": 1499070300000,\n" +
//                    "        \"paymentRefNum\": \"\",\n" +
//                    "        \"welfareCardNo\": \"\",\n" +
//                    "        \"corporate\": \"\",\n" +
//                    "        \"refId\": \"\",\n" +
//                    "        \"channelName\": \"\",\n" +
//                    "        \"submerChantList\": [\n" +
//                    "            {\n" +
//                    "                \"txnRefId\": \"\",\n" +
//                    "                \"amount\": 100.1,\n" +
//                    "                \"companyCode\": \"\"\n" +
//                    "            }\n" +
//                    "        ],\n" +
//                    "        \"branchId\": \"\",\n" +
//                    "        \"orderNo\": \"\",\n" +
//                    "        \"barcode\": \"\",\n" +
//                    "        \"paymentNo\": \"\",\n" +
//                    "        \"voucherAmount\": 10.01,\n" +
//                    "        \"creditCardFee\": 10.1,\n" +
//                    "        \"topupAmount\": 1.1,\n" +
//                    "        \"fromBank\": \"\"\n" +
//                    "    },\n" +
//                    "    \"copay\": {\n" +
//                    "        \"txnRefID\": \"\",\n" +
//                    "        \"totalAmount\": 10.1,\n" +
//                    "        \"walletType\": \"\",\n" +
//                    "        \"barCode\": \"\",\n" +
//                    "        \"wallets\": [\n" +
//                    "            {\n" +
//                    "                \"txnRefID\": \"\",\n" +
//                    "                \"amount\": 100.1,\n" +
//                    "                \"walletType\": \"\"\n" +
//                    "            }\n" +
//                    "        ]\n" +
//                    "    }\n" +
//                    "}";

            ProducerRecord<String, String> record = new ProducerRecord<>(a.get(0), messageId, sampleMessage);
            System.out.println("Good boy");
            producer.send(record).get();

            log.info("Message sent successfully -> {}", messageId);

            System.out.println("Hello");

        } catch (Exception e) {
            log.error("Unable to send message", e);
        }
    }

}
