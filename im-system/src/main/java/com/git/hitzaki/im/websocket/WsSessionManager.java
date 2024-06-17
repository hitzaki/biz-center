package com.git.hitzaki.im.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * session管理器
 * @author hitzaki
 */
@Component
public class WsSessionManager {
    public static final Set<Session> set = new HashSet<>();


    public Map<Session, String> sessionNameMap = new ConcurrentHashMap<>();

    public AtomicInteger no = new AtomicInteger(0);
}
