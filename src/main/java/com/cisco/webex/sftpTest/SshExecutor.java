package com.cisco.webex.sftpTest;

import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author biebi@cisco.com
 * @date 2022/2/15
 */
public class SshExecutor {

    private static final Logger log = LoggerFactory.getLogger(SshExecutor.class);

    public SshExecutor(){}

    public int execute(String cmd, ClientSession session) {
        int ret = -2;
        try (ChannelExec exec = session.createExecChannel(cmd)){
            if (!exec.open().verify(Duration.ofSeconds(60)).isOpened()) {
                throw new Exception("failed to open the session");
            }

            //get output
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ByteArrayOutputStream errput = new ByteArrayOutputStream();
            exec.setOut(output);
            exec.setErr(errput);

            List<ClientChannelEvent> list = new ArrayList<>();
            list.add(ClientChannelEvent.CLOSED);
            exec.waitFor(list, Duration.ofSeconds(60));
            exec.close();
            String result = output.toString();
            String error = errput.toString();
        } catch (IOException e) {
            log.error(cmd,e);
        } catch (Exception e) {
            log.error(cmd,e);
        }

        return ret;
    }

    public int exec(String command,ClientSession session) throws Exception {
        return execute(command, session);
    }
}
