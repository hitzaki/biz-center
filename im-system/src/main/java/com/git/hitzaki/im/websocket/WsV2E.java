package com.git.hitzaki.im.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * @author hitzaki
 */
@ServerEndpoint("/wv2")
@Component
public class WsV2E {

    private static final WsSessionManager wsSessionManager = new WsSessionManager();

    /**
     * 连接成功*/
    @OnOpen
    public void onOpen(Session session) {
        AtomicInteger no = wsSessionManager.no;
        Map<Session, String> sessionNameMap = wsSessionManager.sessionNameMap;
        int i = no.get();
        while (!no.compareAndSet(i, i+1)){
            i = no.get();
        }
        sessionNameMap.put(session, Integer.toString(i+1));
        String format = String.format("用户%s上线", i+1);
        Message message = new Message("system",format);
        System.out.println(format);
        sessionNameMap.forEach((key, value)->{
            if (!session.equals(key)){
                try {
                    session.getBasicRemote().sendText(JSON.toJSONString(message));
                } catch (IOException e) {
                    throw new RuntimeException("发送消息错误");
                }
            }
        });
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        AtomicInteger no = wsSessionManager.no;
        Map<Session, String> sessionNameMap = wsSessionManager.sessionNameMap;

        sessionNameMap.remove(session);
        String format = String.format("用户%s下线", sessionNameMap.get(session));
        Message message = new Message("system",format);
        System.out.println(format);
        sessionNameMap.forEach((key, value)->{
            if (!session.equals(key)){
                try {
                    session.getBasicRemote().sendText(JSON.toJSONString(message));
                } catch (IOException e) {
                    throw new RuntimeException("发送消息错误");
                }
            }
        });
    }

    /**
     * 收到消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        AtomicInteger no = wsSessionManager.no;
        Map<Session, String> sessionNameMap = wsSessionManager.sessionNameMap;

//        String format = String.format("用户%s发送消息(Time: %s): \n  %s",
//                sessionNameMap.get(session), message, LocalDateTime.now());
//        System.out.println(format);
        sessionNameMap.forEach((key, value)->{
            if (!session.equals(key)){
                try {
                    session.getBasicRemote().sendText(JSON.toJSONString(message));
                } catch (IOException e) {
                    throw new RuntimeException("发送消息错误");
                }
            }
        });
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
