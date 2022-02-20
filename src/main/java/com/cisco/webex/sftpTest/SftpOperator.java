package com.cisco.webex.sftpTest;

import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.sshd.client.ClientBuilder;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.sftp.client.SftpClientFactory;
import org.apache.sshd.sftp.client.fs.SftpFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author biebi@cisco.com
 * @date 2022/2/15
 */
public class SftpOperator implements FtpInterface{

    private static final Logger log = LoggerFactory.getLogger(SftpOperator.class);

    private SshClient client;
    private ClientSession session;
    private SftpFileSystem fs;
    private String host;
    private int port;
    private String username;
    private String password;

    public SftpOperator(String host,int port,String username,String password){
        super();
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public FtpInterface login() {
        try{
            client = ClientBuilder.builder().build();
            client.start();
            session = client.connect(username, host, port).verify(Duration.ofSeconds(7l)).getSession();
            session.addPasswordIdentity(password);
            if(session.auth().verify(Duration.ofSeconds(5l)).isFailure()){
                session.close();
                log.warn("sftp password invalid");
                throw new RuntimeException("sftp auth fail");
            }
            fs = SftpClientFactory.instance().createSftpFileSystem(session);
            log.info("sftp login success");
        }catch (Exception e){
            log.error("login fail",e);
        }
        return this;
    }

    @Override
    public void logout() throws IOException {
        fs.close();
        session.close();
        client.stop();
    }

    @Override
    public void upload(String ftpDir, String ftpName, String filename) throws IOException {
        Path remoteRoot = fs.getDefaultDir().resolve(ftpDir);
        if(!Files.exists(remoteRoot)){
            Files.createDirectories(remoteRoot);
        }
        Path remoteFile = remoteRoot.resolve(ftpName);
        Files.deleteIfExists(remoteFile);
        Files.copy(Paths.get(filename),remoteFile,StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public void upload(String ftpDir, String ftpName, InputStream inputStream) throws IOException {
        Path remoteRoot = fs.getDefaultDir().resolve(ftpDir);
        if(!Files.exists(remoteRoot)){
            Files.createDirectories(remoteRoot);
        }
        Path remoteFile = remoteRoot.resolve(ftpName);
        Files.deleteIfExists(remoteFile);
        Files.copy(inputStream,remoteFile);
    }

    @Override
    public void download(String ftpDir, String ftpName, String filename) throws IOException {
        Files.deleteIfExists(Paths.get(filename));
        Path remoteRoot = fs.getDefaultDir().resolve(ftpDir).resolve(ftpName);
        Files.copy(remoteRoot,Paths.get(filename));
    }

    @Override
    public void download(String ftpDir, String ftpName, OutputStream outputStream) throws IOException {
        Path remoteRoot = fs.getDefaultDir().resolve(ftpDir).resolve(ftpName);
        Files.copy(remoteRoot,outputStream);
    }

    @Override
    public void delete(String ftpDir, String ftpName) throws IOException {
        Path remoteRoot = fs.getDefaultDir().resolve(ftpDir);
        if(StringUtils.isEmpty(ftpName)){
            Files.deleteIfExists(remoteRoot);
            return;
        }
        Files.deleteIfExists(remoteRoot.resolve(ftpName));
    }

    @Override
    public void mkdir(String ftpDir) throws IOException {
        Path remoteRoot = fs.getDefaultDir().resolve(ftpDir);
        if(!Files.exists(remoteRoot)){
            Files.createDirectories(remoteRoot);
        }
    }

    @Override
    public boolean exists(String path) {
        return Files.exists(fs.getDefaultDir().resolve(path));
    }

    @Override
    public List<Path> listFile(String ftpDir) throws IOException {
        Path remoteRoot = fs.getDefaultDir().resolve(ftpDir);
        return Files.list(remoteRoot).collect(Collectors.toList());
    }


    @Override
    public Long fileSize(String ftpDir,String ftpName) throws IOException {
        Path remoteRoot = fs.getDefaultDir().resolve(ftpDir).resolve(ftpName);
        return Files.size(remoteRoot);
    }

    @Override
    public void chownAndChmodCascade(String ftpDir) throws Exception {
        SshExecutor executor = new SshExecutor();
        String cmd = "sudo chmod 777 "+ ftpDir;
        executor.exec(cmd,session);
    }


}
