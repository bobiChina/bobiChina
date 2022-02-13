package com.cisco.webex.service.impl;

import com.cisco.webex.service.SSHPoolService;
import com.cisco.webex.service.SSHService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author biebi@cisco.com
 * @date 2022/2/13
 */
@Service
public class SSHServiceImpl implements SSHService {

    private static final Logger log = LoggerFactory.getLogger(SSHServiceImpl.class);

    @Resource
    SSHPoolService poolService;

    @Override
    public void testThreadPool() {
        int count = 200;
        CompletableFuture[] futures = new CompletableFuture[count];
        for (int i = 0; i < count; i++) {
            futures[i] = poolService.sshdHandle();
        }

        CompletableFuture.allOf(futures).join();

        Arrays.stream(futures).forEach(completableFuture -> {
            try {
                System.out.println(completableFuture.get());
            } catch (InterruptedException e) {
                log.error(completableFuture.toString(),e);
            } catch (ExecutionException e) {
                log.error(completableFuture.toString(),e);
            }
        });
    }
}
