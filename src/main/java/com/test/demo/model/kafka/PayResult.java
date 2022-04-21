package com.test.demo.model.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayResult {

    private HeaderReq headerReq;

    private PayResultContent content;

    @Override
    public String toString() {
        return "PayResult{" +
                "headerReq=" + headerReq +
                ", content=" + content +
                '}';
    }
}
