package com.test.demo.service;

import com.test.demo.model.NotificationProducerBean;
import com.test.demo.model.YCYDTxnConsumer;
import com.test.demo.util.DatetimeUtils;
import com.test.demo.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NotificationService {

    @Value("${spring.jackson.date-format}")
    protected String datetimeFormat;

    @Value("${noti.data.title}")
    protected String title;

    @Value("${noti.data.body}")
    protected String body;

    @Value("${noti.data.priority}")
    protected String priority;

    @Value("${noti.data.dry-run}")
    protected boolean dryRun;

    @Value("${noti.data.time-to-live}")
    protected Long timeToLive;

    @Value("${noti.data.content-available}")
    protected boolean contentAvailable;


    public NotificationProducerBean buildNotificationProducerBean(YCYDTxnConsumer ycydTxnConsumer)
    {
        log.info("buildNotificationProducerBean begin.");
        NotificationProducerBean notificationProducerBean = new NotificationProducerBean();
        List<String> dataDict = new ArrayList<>();
        dataDict.add("screen:Transaction");
        String messageTitle = StringUtils.replace(title,
                new String[]{"{amount}"},
                new String[]{StringUtils.currency(ycydTxnConsumer.getAmount())});
            
        LocalDateTime currentDatetime = DatetimeUtils.now();
        String createAt = DatetimeUtils.formatThaiDate(currentDatetime, "d MMM yyyy HH:mm:ss น.");
        String messageBody = StringUtils.replace(body,
                new String[]{"{fromAccountName}", "{txnDateTime}"},
                new String[]{StringUtils.formatPayerName(ycydTxnConsumer.getPayerName()),
                        createAt});
        log.info("message Noti Body : {}" ,messageBody);
        notificationProducerBean = NotificationProducerBean.builder()
                .merchantId(ycydTxnConsumer.getMerchantId())
                .branchId(ycydTxnConsumer.getBranchId())
                //.token()
                .title(messageTitle)
                .body(messageBody)
                .priority(priority)
                .icon("ic_launcher")
                .clickAction(".HomeActivity")
                .dataDict(dataDict)
                .build();

        log.info("buildNotificationProducerBean end. {} ",notificationProducerBean);
        return notificationProducerBean;
    }

}
