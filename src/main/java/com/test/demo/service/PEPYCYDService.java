package com.test.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.TnYcydListenerPaymentResultApplication;
import com.test.demo.entity.TxnLogYcYd;
import com.test.demo.log.ListenerLogger;
import com.test.demo.model.MerchantInfobean;
import com.test.demo.model.NotificationProducerBean;
import com.test.demo.model.YCYDTxnConsumer;
import com.test.demo.model.kafka.PayResult;
import com.test.demo.repository.TxnYCYDLogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class PEPYCYDService {
    private static final ListenerLogger logger = new ListenerLogger(PEPYCYDService.class);
    private static final Logger log = LogManager.getLogger(TnYcydListenerPaymentResultApplication.class);
    private final ObjectMapper objectMapper;

    @Autowired
    @Qualifier("profileRedisTemplate")
    private  RedisTemplate<String, Object> profileRedisTemplate;

    @Autowired
    private TxnYCYDLogRepository txnYCYDLogRepository;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private NotificationTnPepService notificationService;

    @Autowired
    private PayResultTnPepService payResultService;

    public PEPYCYDService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void pepYcydTxn(String data) throws Exception {
        YCYDTxnConsumer ycydTxnConsumer = objectMapper.readValue(data, YCYDTxnConsumer.class);
        MerchantInfobean merchantInfobean = new MerchantInfobean();
        TxnLogYcYd txnYCYDLog = null;

        try {
            ycydTxnConsumer = validateAmount(ycydTxnConsumer);
            String key = "MERCHANT:" + ycydTxnConsumer.getMerchantId() + ":" + ycydTxnConsumer.getBranchId();
            log.info("get Merchant Profile from redis key : {} ",key);
            String merchantProfile = (String) profileRedisTemplate.opsForValue().get(key);
            log.info("values Merchant Profile from redis key : {} ",merchantProfile);
            merchantInfobean = objectMapper.readValue(merchantProfile,MerchantInfobean.class);
            log.info("merchant profile objectMapper.readValue : {} ",merchantInfobean);
            txnYCYDLog = buildTxnYCYDLog(ycydTxnConsumer , merchantInfobean);

            NotificationProducerBean notificationProducerBean = notificationService.buildNotificationProducerBean(ycydTxnConsumer);
            String notificationJson = objectMapper.writeValueAsString(notificationProducerBean);
            kafkaService.sendKafkaNotification(notificationJson);
            logger.info("sendKafkaNotification success.");

            PayResult payResult = payResultService.generatePayResult(ycydTxnConsumer , merchantInfobean , txnYCYDLog);
            String payResultJson = objectMapper.writeValueAsString(payResult);
            kafkaService.sendToKafkaPayResult(payResultJson);
            logger.info("sendToKafkaPayResult success");

            txnYCYDLogRepository.save(txnYCYDLog);
            logger.info("txnYCYDLogRepository.save success.");

        }
        catch (Exception e) {
            logger.error("Error: ", e);
            logger.error("Message: {}", data);
        }
    }

    private YCYDTxnConsumer validateAmount(YCYDTxnConsumer ycydTxnConsumer)
    {
        if (ycydTxnConsumer.getGwalletAmount() == null){
            ycydTxnConsumer.setGwalletAmount("0");
        }
        if (ycydTxnConsumer.getEvoucherAmount() == null){
            ycydTxnConsumer.setEvoucherAmount("0");
        }
        return ycydTxnConsumer;
    }

    public TxnLogYcYd buildTxnYCYDLog(YCYDTxnConsumer ycydTxnConsumer , MerchantInfobean merchantInfobean)
    {
        log.info("buildTxnYCYDLog begin.");
        LocalDateTime processDate =  LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(ycydTxnConsumer.getTxnDtm())), ZoneId.systemDefault());
        LocalDateTime createdAt = LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault());
        TxnLogYcYd txnYCYDLog = TxnLogYcYd.builder()
                .qrRefId(ycydTxnConsumer.getQrRefId())
                .txnRefId(ycydTxnConsumer.getTxnRefId())
                .merchantId(ycydTxnConsumer.getMerchantId())
                .merchantName(merchantInfobean.getMerchantName())
                .branchId(ycydTxnConsumer.getBranchId())
                .merchantAccountNo(merchantInfobean.getAccountNos().get(0).getAccountNo())
                .merchantAccountName(merchantInfobean.getAccountNos().get(0).getAccountName())
                .amount(new BigDecimal(ycydTxnConsumer.getAmount()))
                .evoucherAmount(new BigDecimal(ycydTxnConsumer.getEvoucherAmount()))
                .gwalletAmount(new BigDecimal(ycydTxnConsumer.getGwalletAmount()))
                .customerCid(ycydTxnConsumer.getCitizenId())
                .processDate(processDate)
                .settleType(ycydTxnConsumer.getSettleType())
                .deviceUuid(ycydTxnConsumer.getDeviceUuid())
                .companyCode(ycydTxnConsumer.getCompanyCode())
                .customerName(ycydTxnConsumer.getPayerName())
                .customerMobile(ycydTxnConsumer.getPayerId())
                .latitude(ycydTxnConsumer.getMerchantLatitude())
                .longitude(ycydTxnConsumer.getMerchantLongitude())
                .merchantTaxId(merchantInfobean.getMerchantTaxId())
                .channel(ycydTxnConsumer.getChannelName())
                .createdAt(createdAt)
                .branchName(merchantInfobean.getBranchName())
                .branchNo(merchantInfobean.getBranchNo())
                .branchMobileNo(merchantInfobean.getMerchantMobile())
                .kcorpId(merchantInfobean.getKcorpId())
                .build();
        log.info("buildTxnYCYDLog end {}",txnYCYDLog);
        return txnYCYDLog;
    }
}
