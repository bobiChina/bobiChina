package com.cisco.webex.controller;

import com.cisco.webex.service.SSHService;
import com.cisco.webex.sftpTest.FtpInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author biebi@cisco.com
 * @date 2022/2/13
 */
@RestController
@RequestMapping("ssh")
public class SSHController {
    private static final Logger log = LoggerFactory.getLogger(SSHController.class);

    @Resource
    SSHService sshService;

    @GetMapping("/ok")
    public String isOk(){
        return "is ok ....";
    }

    @GetMapping("/threadPool")
    public void testThreadPool(){
        sshService.testThreadPool();
    }

    @GetMapping("/sftpFileSystem")
    public void testSftpFileSystem(){
        sshService.testSftpFileSystem();
    }
}
