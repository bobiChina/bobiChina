package com.cisco.webex.controller;

import com.cisco.webex.config.PoolConfig;
import org.apache.sshd.client.ClientBuilder;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.session.SessionHeartbeatController;
import org.apache.sshd.common.util.threads.ThreadUtils;
import org.apache.sshd.sftp.client.SftpClient;
import org.apache.sshd.sftp.client.SftpClientFactory;
import org.apache.sshd.sftp.client.fs.SftpFileSystem;
import org.apache.sshd.sftp.client.fs.SftpFileSystemProvider;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.sql.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author biebi@cisco.com
 * @date 2022/2/13
 */
public class TestSFTP {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        SshClient sshClient = ClientBuilder.builder().build();
        sshClient.setSessionHeartbeat(SessionHeartbeatController.HeartbeatType.IGNORE, Duration.ofSeconds(10l));
        sshClient.start();


        /*ConnectFuture verifySession = sshClient
                .connect("smsops", "10.224.56.231", 22)
                .verify(PoolConfig.connectTimeout);
        ClientSession session = verifySession.getSession();
        session.addPasswordIdentity("smsops");
        session.setSessionHeartbeat(SessionHeartbeatController.HeartbeatType.IGNORE, Duration.ofSeconds(10l));

        SftpClientFactory sftpClientFactory = SftpClientFactory.instance();*/

        /*URI sftpFileSystemURI = SftpFileSystemProvider.createFileSystemURI("10.224.56.231", 22, "smsops", "smsops");
        FileSystem fileSystem = FileSystems.getFileSystem(sftpFileSystemURI);
        Path path = fileSystem.getPath("/tmp/testsftp.txt");
        File file = path.toFile();
        file.setReadOnly();*/

        /*SftpFileSystemProvider provider = new SftpFileSystemProvider(sshClient);
        URI uri = SftpFileSystemProvider.createFileSystemURI("10.224.56.231", 22, "smsops", "smsops");
        try(FileSystem fs = provider.getFileSystem(uri)){
            Path remotePath = fs.getPath("/tmp/testsftp.txt");
            System.out.println(remotePath.getFileName());
        }*/


        /*SftpFileSystem fs = sftpClientFactory.createSftpFileSystem(session,1024,1024);
        *//*fs.setReadBufferSize(1024);
        fs.setWriteBufferSize(1024);*//*

        Path remoteRoot = fs.getDefaultDir().resolve("/tmp");
        Path remote0 = remoteRoot.resolve("testsftp.txt");
        //Files.deleteIfExists(remote0);

        Path localRoot = Paths.get(new URI("C:\\Temp"));
        Path local0 = localRoot.resolve("testsftp.txt");

        Files.copy(local0, remote0);*/
    }
}
