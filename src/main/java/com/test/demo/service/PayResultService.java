package com.test.demo.service;

import com.test.demo.entity.TxnLogYcYd;
import com.test.demo.model.MerchantInfobean;
import com.test.demo.model.YCYDTxnConsumer;
import com.test.demo.model.kafka.HeaderReq;
import com.test.demo.model.kafka.PayResult;
import com.test.demo.model.kafka.PayResultContent;
import com.test.demo.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.UUID;

@Service
@Slf4j
public class PayResultService {

    @Value("${spring.kafka.producer.topic.topic-pay-result}")
    private String topicPayResult;

    public PayResult generatePayResult(YCYDTxnConsumer ycydLog, MerchantInfobean responseMerchantInfo, TxnLogYcYd txnYCYDLog) {

        log.info("generatePayResult begin.");
        PayResult payResult = new PayResult();
        PayResultContent payResultContent = new PayResultContent();
        if (ycydLog != null) {
            String walletType = mapPaymentType(ycydLog.getEvoucherAmount(),ycydLog.getGwalletAmount());
            try {
                payResultContent.setTxnRefID(ycydLog.getTxnRefId());
                payResultContent.setMerchantId(ycydLog.getMerchantId());
                payResultContent.setMerchantChannelId(Constant.GP);
                payResultContent.setRefId(ycydLog.getQrRefId());
                payResultContent.setMerchantName(responseMerchantInfo.getMerchantName());
                payResultContent.setMerchantAccNum(responseMerchantInfo.getAccountNos().get(0).getAccountNo());
                payResultContent.setCompanyCode(responseMerchantInfo.getCompanyCode());
                payResultContent.setWalletChannelId(Constant.YCYD);
                payResultContent.setWalletId(txnYCYDLog.getCustomerCid());
                payResultContent.setWalletType(Constant.SPEND_TO_GET);
                payResultContent.setWalletRefId(null);
                payResultContent.setPayerId(txnYCYDLog.getCustomerCid());
                payResultContent.setPayerName(txnYCYDLog.getCustomerName());
                payResultContent.setPaymentAmount(new BigDecimal(ycydLog.getAmount()));
                payResultContent.setQrCreateTimestamp(null);
                payResultContent.setProcessTimestamp(txnYCYDLog.getProcessDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                payResultContent.setPaymentRefNum(txnYCYDLog.getTxnRefId());
                payResultContent.setCorporate(null);
                payResultContent.setChannelName("PT");
                payResultContent.setSubmerChantList(null);
                payResultContent.setBranchId(ycydLog.getBranchId());
                payResultContent.setOrderNo(null);
                payResultContent.setBarcode(null);
                payResultContent.setPaymentNo(null);
                payResultContent.setCreditCardFee(BigDecimal.ZERO);
                payResultContent.setVoucherAmount(new BigDecimal(ycydLog.getEvoucherAmount()));
                payResultContent.setTopupAmount(new BigDecimal(ycydLog.getGwalletAmount()));
                payResultContent.setPaymentType(walletType);
                payResultContent.setMerchantLatitude(txnYCYDLog.getLatitude());
                payResultContent.setMerchantLongitude(txnYCYDLog.getLongitude());
                payResultContent.setChannel(ycydLog.getChannelName());
        		payResultContent.setStatusCode("0000");
        		payResultContent.setStatusDesc("SUCCESS");
                payResultContent.setSettleType(ycydLog.getSettleType());
        		payResultContent.setPayerId("**** " + txnYCYDLog.getCustomerCid().substring(txnYCYDLog.getCustomerCid().length() - 4));
        		payResultContent.setPayerName(txnYCYDLog.getCustomerName());

                if (!ObjectUtils.isEmpty(responseMerchantInfo) && !ObjectUtils.isEmpty(responseMerchantInfo)) {

                    payResultContent.setMerchantType(responseMerchantInfo.getMerchantCategoryCode());
                    if (!ObjectUtils.isEmpty(responseMerchantInfo.getMerchantCategoryDisplay())) {
                        payResultContent.setMerchantTypeDesc(responseMerchantInfo.getMerchantCategoryDisplay());
                    }

                }

                payResult.setHeaderReq(getHeaderReq());
                payResult.setContent(payResultContent);

            } catch (Exception e) {
                log.error("generatePayResult Exception : ", e);
            }
        }
        log.info("generatePayResult end : {} " , payResult);
        return payResult;
    }
    private HeaderReq getHeaderReq() {
        HeaderReq headerReq = HeaderReq.builder()
                .channelId("TN")
                .reqId(UUID.randomUUID().toString())
                .service(topicPayResult)
                .build();
        return headerReq;
    }

    private String mapPaymentType(String evoucherAmount, String gwalletAmount) {
        if (evoucherAmount.equals("0") && Float.parseFloat(gwalletAmount)>0){
            return "G";
        }
        return "";
    }

    public PayResult generatePepYcydPayResult(YCYDTxnConsumer ycydLog, MerchantInfobean responseMerchantInfo, TxnLogYcYd txnYCYDLog) {

        log.info("generatePayResult begin.");
        PayResult payResult = new PayResult();
        PayResultContent payResultContent = new PayResultContent();
        if (ycydLog != null) {
            String walletType = mapPaymentType(ycydLog.getEvoucherAmount(),ycydLog.getGwalletAmount());
            try {
                payResultContent.setTxnRefID(ycydLog.getTxnRefId());
                payResultContent.setMerchantId(ycydLog.getMerchantId());
                payResultContent.setMerchantChannelId(Constant.GP);
                payResultContent.setRefId(ycydLog.getQrRefId());
                payResultContent.setMerchantName(responseMerchantInfo.getMerchantName());
                payResultContent.setMerchantAccNum(responseMerchantInfo.getAccountNos().get(0).getAccountNo());
                payResultContent.setCompanyCode(responseMerchantInfo.getCompanyCode());
                payResultContent.setWalletChannelId(Constant.YCYD);
                payResultContent.setWalletId(txnYCYDLog.getCustomerCid());
                payResultContent.setWalletType(Constant.SPEND_TO_GET);
                payResultContent.setWalletRefId(null);
                payResultContent.setPayerId(txnYCYDLog.getCustomerCid());
                payResultContent.setPayerName(txnYCYDLog.getCustomerName());
                payResultContent.setPaymentAmount(new BigDecimal(ycydLog.getAmount()));
                payResultContent.setQrCreateTimestamp(null);
                payResultContent.setProcessTimestamp(txnYCYDLog.getProcessDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                payResultContent.setPaymentRefNum(txnYCYDLog.getTxnRefId());
                payResultContent.setCorporate(null);
                payResultContent.setChannelName("PT");
                payResultContent.setSubmerChantList(null);
                payResultContent.setBranchId(ycydLog.getBranchId());
                payResultContent.setOrderNo(null);
                payResultContent.setBarcode(null);
                payResultContent.setPaymentNo(null);
                payResultContent.setCreditCardFee(BigDecimal.ZERO);
                payResultContent.setVoucherAmount(new BigDecimal(ycydLog.getEvoucherAmount()));
                payResultContent.setTopupAmount(new BigDecimal(ycydLog.getGwalletAmount()));
                payResultContent.setPaymentType(walletType);
                payResultContent.setMerchantLatitude(txnYCYDLog.getLatitude());
                payResultContent.setMerchantLongitude(txnYCYDLog.getLongitude());
                payResultContent.setChannel(ycydLog.getChannelName());
                payResultContent.setStatusCode("0000");
                payResultContent.setStatusDesc("SUCCESS");
                payResultContent.setSettleType(ycydLog.getSettleType());
                payResultContent.setPayerId("**** " + txnYCYDLog.getCustomerCid().substring(txnYCYDLog.getCustomerCid().length() - 4));
                payResultContent.setPayerName(txnYCYDLog.getCustomerName());

                if (!ObjectUtils.isEmpty(responseMerchantInfo) && !ObjectUtils.isEmpty(responseMerchantInfo)) {

                    payResultContent.setMerchantType(responseMerchantInfo.getMerchantCategoryCode());
                    if (!ObjectUtils.isEmpty(responseMerchantInfo.getMerchantCategoryDisplay())) {
                        payResultContent.setMerchantTypeDesc(responseMerchantInfo.getMerchantCategoryDisplay());
                    }
                }


                payResult.setHeaderReq(getHeaderReq());
                payResult.setContent(payResultContent);

            } catch (Exception e) {
                log.error("generatePayResult Exception : ", e);
            }
        }
        log.info("generatePayResult end : {} " , payResult);
        return payResult;
    }
}
