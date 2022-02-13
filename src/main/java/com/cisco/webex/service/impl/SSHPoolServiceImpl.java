package com.cisco.webex.service.impl;

import com.cisco.webex.service.SSHPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author biebi@cisco.com
 * @date 2022/2/13
 */
@Service
public class SSHPoolServiceImpl implements SSHPoolService {

    private static final Logger log = LoggerFactory.getLogger(SSHPoolServiceImpl.class);

    @Override
    @Async("sshd-pool")
    public CompletableFuture<String> sshdHandle() {
        System.out.println(Thread.currentThread().getName());;
        return CompletableFuture.completedFuture("success");
    }
}
