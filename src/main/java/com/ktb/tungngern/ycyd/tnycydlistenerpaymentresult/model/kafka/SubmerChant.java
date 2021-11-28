package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.model.kafka;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubmerChant {
    private String txnRefId;
    private BigDecimal amount;
    private String companyCode;

    public SubmerChant() {

    }

    public SubmerChant(String txnRefId, BigDecimal amount, String companyCode) {
        this.txnRefId = txnRefId;
        this.amount = amount;
        this.companyCode = companyCode;
    }
}

