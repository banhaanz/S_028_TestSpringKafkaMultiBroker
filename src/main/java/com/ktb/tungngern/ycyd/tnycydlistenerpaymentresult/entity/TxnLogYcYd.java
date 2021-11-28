package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "ycyd_txn_log")
public class TxnLogYcYd {

    @Id
    @Column(name="QR_REF_ID")
    private String qrRefId;
    @Column(name="TXN_REF_ID")
    private String txnRefId;
    @Column(name="MERCHANT_ID")
    private String merchantId;
    @Column(name="MERCHANT_NAME")
    private String merchantName;
    @Column(name="BRANCH_ID")
    private String branchId;
    @Column(name="MERCHANT_ACCOUNT_NO")
    private String merchantAccountNo;
    @Column(name="MERCHANT_ACCOUNT_NAME")
    private String merchantAccountName;
    @Column(name="EVOUCHER_AMOUNT")
    private BigDecimal evoucherAmount;
    @Column(name="AMOUNT")
    private BigDecimal amount;
    @Column(name="CUSTOMER_CID")
    private String customerCid;
    @Column(name="PROCESS_DATE")
    private LocalDateTime processDate;
    @Column(name="SETTLE_TYPE")
    private String settleType;
    @Column(name="DEVICE_UUID")
    private String deviceUuid;
    @Column(name="COMPANY_CODE")
    private String companyCode;
    @Column(name="CUSTOMER_NAME")
    private String customerName;
    @Column(name="CUSTOMER_MOBILE")
    private String customerMobile;
    @Column(name="LATITUDE")
    private String latitude;
    @Column(name="LONGITUDE")
    private String longitude;
    @Column(name="MERCHANT_TAX_ID")
    private String merchantTaxId;
    @Column(name="CHANNEL")
    private String channel;
    @Column(name="REF_KEY")
    private String refKey;
    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;
    @Column(name="BRANCH_NAME")
    private String branchName;
    @Column(name="BRANCH_NO")
    private String branchNo;
    @Column(name="BRANCH_MOBILE_NO")
    private String branchMobileNo;
    @Column(name="GWALLET_AMOUNT")
    private BigDecimal gwalletAmount;
    @Column(name="KCORP_ID")
    private String kcorpId;

}
