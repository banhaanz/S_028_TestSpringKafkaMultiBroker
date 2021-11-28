package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationProducerBean {

    public String merchantId;
    public String branchId;
    public String token;
    public String title;
    public String body;
    public String priority;
    public String icon;
    public String clickAction;
    private List<String> dataDict;

}
