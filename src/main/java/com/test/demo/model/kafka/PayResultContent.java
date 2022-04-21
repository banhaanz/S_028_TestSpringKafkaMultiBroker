package com.test.demo.model.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayResultContent {

    private String txnRefID;
    private String merchantId;
    private String merchantChannelId;
    private String merchantName;
    private String merchantAccNum;
    private String companyCode;
    private String merchantType;
    private String merchantTypeDesc;
    private String walletChannelId;
    private String walletId;
    private String walletType;
    private String walletRefId;
    private String payerId;
    private String payerName;
    private String paymentType;
    private BigDecimal paymentAmount;
    private String verifyType;
    private String statusCode;
    private String statusDesc;
    private Long qrCreateTimestamp;
    private Long processTimestamp;
    private String paymentRefNum;
    private String welfareCardNo;
    private String corporate;
    private String refId;
    private String channelName;
    private List<SubmerChant> submerChantList;
    private String branchId;
    private String orderNo;
    private String barcode;
    private String paymentNo;
    private BigDecimal voucherAmount;
    private BigDecimal creditCardFee;
    private BigDecimal topupAmount;
// add for kafka rcn log

    private Long processDate;
    private String accountNo;
    private String accountName;
    private String customerCid;
    private String customerName;
    private BigDecimal totalAmount;
    private String refKey;
    private String block;
    private String merchantLatitude;
    private String merchantLongitude;
    private String channel;
    private String termId;
    private String settleType;

    @Override
    public String toString() {
        return "PayResultContent{" +
                "txnRefID='" + txnRefID + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", merchantChannelId='" + merchantChannelId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", merchantAccNum='" + merchantAccNum + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", merchantType='" + merchantType + '\'' +
                ", merchantTypeDesc='" + merchantTypeDesc + '\'' +
                ", walletChannelId='" + walletChannelId + '\'' +
                ", walletId='****" + '\'' +
                ", walletType='" + walletType + '\'' +
                ", walletRefId='" + walletRefId + '\'' +
                ", payerId='****" + '\'' +
                ", payerName='" + payerName + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", verifyType='" + verifyType + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                ", qrCreateTimestamp=" + qrCreateTimestamp +
                ", processTimestamp=" + processTimestamp +
                ", paymentRefNum='" + paymentRefNum + '\'' +
                ", welfareCardNo='" + welfareCardNo + '\'' +
                ", corporate='" + corporate + '\'' +
                ", refId='" + refId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", submerChantList=" + submerChantList +
                ", branchId='" + branchId + '\'' +
                ", processDate='" + processDate + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", accountName='" + accountName + '\'' +
                ", customerCid='" + customerCid + '\'' +
                ", customerName='" + customerName + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", refKey='" + refKey + '\'' +
                ", block='" + block + '\'' +
                ", merchantLatitude='" + merchantLatitude + '\'' +
                ", merchantLongitude='" + merchantLongitude + '\'' +
                ", channel='" + channel + '\'' +
                ", termId='" + termId + '\'' +
                ", settleType='" + settleType + '\'' +
                '}';
    }
}
