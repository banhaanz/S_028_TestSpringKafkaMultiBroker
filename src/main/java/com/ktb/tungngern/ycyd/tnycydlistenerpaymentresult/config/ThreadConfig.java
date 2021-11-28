package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ThreadConfig {

    @Value("${thread.executor.core-pool-size}")
    private int corePoolSize;

    @Value("${thread.executor.max-pool-size}")
    private int maxPoolSize;

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("tn-ycyd-listener-payment-result-");
        executor.initialize();
        return executor;
    }
}
