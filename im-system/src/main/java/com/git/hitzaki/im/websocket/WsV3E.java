package com.git.hitzaki.im.websocket;

import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * @author hitzaki
 */
@ServerEndpoint("/wv3")
@Component
public class WsV3E {
    private static final Set<Session> set = WsSessionManager.set;
    /**
     * 连接成功*/
    @OnOpen
    public void onOpen(Session session) {
        set.add(session);
        Message message = new Message("system","用户1号上线");
        set.forEach(
                session2 -> {
                    try {
                        session2.getBasicRemote().sendText(JSON.toJSONString(message));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        Message message = new Message("system","用户1号下线");
        set.forEach(
                session2 -> {
                    try {
                        session2.getBasicRemote().sendText(JSON.toJSONString(message));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    /**
     * 收到消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        Message messageM = new Message("用户1",message);
        set.forEach(
                session2 -> {
                    try {
                        session2.getBasicRemote().sendText(JSON.toJSONString(messageM));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

}
