package com.cisco.webex.sftpTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * @author biebi@cisco.com
 * @date 2022/2/15
 */
public interface FtpInterface {

    FtpInterface login();

    void logout() throws IOException;

    void upload(String ftpDir,String ftpName,String filename) throws IOException;

    void upload(String ftpDir, String ftpName, InputStream inputStream) throws IOException;

    void download(String ftpDir,String ftpName,String filename) throws IOException;

    void download(String ftpDir, String ftpName, OutputStream outputStream) throws IOException;

    void delete(String ftpDir,String ftpName) throws IOException;

    void mkdir(String ftpDir) throws IOException;

    boolean exists(String path);

    List<Path> listFile(String ftpDir) throws IOException;

    Long fileSize(String ftpDir,String ftpName) throws IOException;

    void chownAndChmodCascade(String ftpDir) throws Exception;
}
