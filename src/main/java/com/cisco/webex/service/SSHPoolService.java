package com.cisco.webex.service;

import java.util.concurrent.CompletableFuture;

/**
 * @author biebi@cisco.com
 * @date 2022/2/13
 */
public interface SSHPoolService {

    CompletableFuture<String> sshdHandle();
}
