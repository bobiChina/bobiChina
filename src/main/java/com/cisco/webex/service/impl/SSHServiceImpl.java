package com.cisco.webex.service.impl;

import com.cisco.webex.service.SSHPoolService;
import com.cisco.webex.service.SSHService;
import org.apache.sshd.client.ClientBuilder;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.common.session.SessionHeartbeatController;
import org.apache.sshd.sftp.client.fs.SftpFileSystemProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.time.Duration;
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
        int count = 20000;
        CompletableFuture[] futures = new CompletableFuture[count];
        for (int i = 0; i < count; i++) {
            futures[i] = poolService.sshdHandle();
        }

        CompletableFuture.allOf(futures).join();

       /* Arrays.stream(futures).forEach(completableFuture -> {
            try {
                System.out.println(completableFuture.get());
            } catch (InterruptedException e) {
                log.error(completableFuture.toString(),e);
            } catch (ExecutionException e) {
                log.error(completableFuture.toString(),e);
            }
        });*/
    }

    @Override
    public void testSftpFileSystem(){
        SshClient sshClient = ClientBuilder.builder().build();
        sshClient.setSessionHeartbeat(SessionHeartbeatController.HeartbeatType.IGNORE, Duration.ofSeconds(10l));
        sshClient.start();

        SftpFileSystemProvider provider = new SftpFileSystemProvider(sshClient);
        URI uri = SftpFileSystemProvider.createFileSystemURI("10.224.56.231", 22, "smsops", "smsops");
        try{
            FileSystem fs = provider.getFileSystem(uri);
            Path remotePath = fs.getPath("/tmp/testsftp.txt");
            System.out.println(remotePath.getFileName());
        }catch (Exception e){
            log.error("sftp error",e);
        }

    }
}
