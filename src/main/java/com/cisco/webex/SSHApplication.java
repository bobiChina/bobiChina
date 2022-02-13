package com.cisco.webex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author biebi@cisco.com
 * @date 2022/2/13
 */
@SpringBootApplication
@EnableAsync
public class SSHApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSHApplication.class,args);
    }
}
