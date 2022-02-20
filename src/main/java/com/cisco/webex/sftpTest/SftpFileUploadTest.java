package com.cisco.webex.sftpTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

/**
 * @author biebi@cisco.com
 * @date 2022/2/15
 */
public class SftpFileUploadTest {

    private static final Logger log = LoggerFactory.getLogger(SftpFileUploadTest.class);

    public static void main(String[] args) {
        FtpInterface ftp = new SftpOperator("10.224.56.231",22,"smsops","smsops");
        try {
            ftp.login();
        } catch (Exception e) {
            log.error("login error",e);
        }

        try {
            //ftp.upload("/hello","test.txt","C:\\sms\\logs\\t1.txt");

            //InputStream is = new FileInputStream(new File("C:\\sms\\logs"));
            //ftp.upload("/hello","test.txt",is);

            //ftp.upload("/hello/createDir","C:\\sms\\logs");

            //OutputStream os = new FileOutputStream(new File("C:\\sms\\log\\t1.txt"));
            //ftp.download("/hello","test.txt",os);

            //ftp.delete("/hello","test.txt");

            //ftp.mkdir("/hello/test/sshd/sfp");

            System.out.println(ftp.exists("/hello/stmp/stmepl/2.text"));

            //List<Path> list = ftp.list("/sms/logs");
            //list.stream().forEach(filename -> System.out.println(filename));

            /*List<Path> list = ftp.listFile("/sms/logs");
            list.stream().forEach(filePath -> {
                try {
                    //System.out.println(filePath.getFileName().toString());
                    System.out.println(ftp.fileSize("/sms/logs",filePath.getFileName().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });*/
            //System.out.println(ftp.getFileSize("/sms/logs/", "webex-ng-sms-nbr-migration-2022-01-27-1.log"));

            ftp.chownAndChmodCascade("/hello/createDir");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
