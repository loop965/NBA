package com.yf.producer.util;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.omg.SendingContext.RunTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yf
 * @date: 2020/07/24  16:04
 * @desc:
 */
public class SSHClient {

    private String ip;
    private String username;
    private String password;

    private String charset = Charset.defaultCharset().toString();
    private static final int TIME_OUT = 1000 * 5 * 60;

    private Connection conn;

    public SSHClient(String ip, String username, String password) {
        this.ip = ip;
        this.username = username;
        this.password = password;
    }

    /**
     * 登录指远程服务器
     * @return
     * @throws IOException
     */
    private boolean login() throws IOException {
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(username, password);
    }

    public int exec(String shell) throws Exception {
        int ret = -1;
        try {
            if (login()) {
                Session session = conn.openSession();
                session.execCommand(shell);
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                ret = session.getExitStatus();
                InputStream is = new StreamGobbler(session.getStdout());
                BufferedReader brs = new BufferedReader(new InputStreamReader(is, "utf-8"));
                List<String> result = new ArrayList<String>();
                for (String line = brs.readLine(); line != null; line = brs.readLine()) {
                    result.add(line);
                    System.out.println(line);
                }
                if(result.size() ==0) {
                    System.out.println(result);
                }
            } else {
                // 自定义异常类 实现略
                throw new Exception("登录远程机器失败" + ip);
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return ret;
    }

    public static void main(String[] args){
//        try {
//            SSHClient sshClient = new SSHClient("139.196.81.168", "root", "sxG45tcBlCyPowTY");
//            int result = sshClient.exec("/mysoft/modoo/modoo-interactive/startup.sh");
////            int result = sshClient.exec("ifconfig");
//
//            System.out.println(result);
////            Process exec = Runtime.getRuntime().exec("cmd /c start E:\\modoo\\modoo-service\\modoo-interactive\\deploy_prod.bat");
//            System.out.println();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        HttpRequest httpRequest = HttpRequest.post("https://www.extfans.com/tampermonkey/download/563ee798a171c7e2870265e9151fbf01?code=555555");
        String r = httpRequest.execute().body();
        System.out.println(r);
    }


}
