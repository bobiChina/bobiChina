package com.cisco.webex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author biebi@cisco.com
 * @description
 * @date 2022/2/13
 */
@Component
public class ThreadPoolConfig {

    @Resource
    ThreadPoolConfigAttribute attribute;

    @Bean("sshd-pool")
    public ThreadPoolTaskExecutor sshdThreadPool(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(attribute.getCorePoolSize());
        threadPoolTaskExecutor.setMaxPoolSize(attribute.getMaxPoolSize());
//        threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        threadPoolTaskExecutor.setQueueCapacity(attribute.getQueueCapacity());
        threadPoolTaskExecutor.setThreadNamePrefix("ThreadPool-SSHD-");
        threadPoolTaskExecutor.setKeepAliveSeconds(attribute.getKeepAliveSeconds());
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
