package com.zt.memcache.util.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: zhangtan
 * Date: 13-7-16
 * Time: 下午2:16
 */
public class SocketCheckUtil {

    public static boolean   checkOprtIsOk(String ip, int port) {
        boolean rs = false;
        try {
            Socket socket = new Socket(ip, port);
            rs = true;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return rs;
    }
}
