package com.git.hitzaki.im.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hitzaki
 * Websocket链接组件
 */
@ServerEndpoint("/ws")
@Component
public class WsServerEndpoint extends TextWebSocketHandler {

    Map<WebSocketSession, String> sessionNameMap = new ConcurrentHashMap<>();

    AtomicInteger no = new AtomicInteger();
    /**
     * socket 建立成功事件
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        int i = no.get();
        while (!no.compareAndSet(i, i+1)){
            i = no.get();
        }
        sessionNameMap.put(session, Integer.toString(i+1));
//        Object token = session.getAttributes().get("token");
//        if (token != null) {
//            // 用户连接成功，放入在线用户缓存
//            WsSessionManager.add(token.toString(), session);
//        } else {
//            throw new RuntimeException("用户登录已经失效!");
//        }
    }

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        TextMessage textMessage = new TextMessage(String.format("用户%s发送消息(Time: %s): \n  %s",
                sessionNameMap.get(session), payload, LocalDateTime.now()));
        sessionNameMap.forEach((key, value)->{
            if (!session.equals(key)){
                try {
                    key.sendMessage(textMessage);
                } catch (IOException e) {
                    throw new RuntimeException("发送消息错误");
                }
            }
        });
    }

    /**
     * socket 断开连接时
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionNameMap.remove(session);
        TextMessage textMessage = new TextMessage(String.format("用户%s下线", sessionNameMap.get(session)));
        sessionNameMap.forEach((key, value)->{
            if (!session.equals(key)){
                try {
                    key.sendMessage(textMessage);
                } catch (IOException e) {
                    throw new RuntimeException("发送消息错误");
                }
            }
        });
    }

}
