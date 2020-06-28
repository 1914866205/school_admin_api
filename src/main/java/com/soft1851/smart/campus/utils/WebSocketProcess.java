package com.soft1851.smart.campus.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/4
 * @Version 1.0
 */
@Component
@Slf4j
@ServerEndpoint(value = "/testWebSocket/{id}")
public class WebSocketProcess {

    /**
     * 持有每个webSocket对象，以key-value存储到线程安全ConcurrentHashMap
     */
    private static ConcurrentHashMap<Long, WebSocketProcess> concurrentHashMap =
            new ConcurrentHashMap<>();

    /**
     * 客户端创建连接时触发
     */
    private Session session;

    /**
     * 客户端连接时触发
     * @param session
     * @param id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") long id) {
        //每新创建一个连接，就把当前客户id为key，this为value存储到map
        this.session = session;
        concurrentHashMap.put(id, this);
        log.info("Open a websocket. id={}", id);
    }

    /**
     * 客户端关闭连接时触发
     * @param session
     * @param id
     */
    @OnClose
    public void onClose(Session session, @PathParam("id") long id) {
        //客户端连接关闭时，移除map中存储的键值对
        concurrentHashMap.remove(id);
        log.info("close a websocket, concurrentHashMap remove sessionId={}", id);
    }

    /**
     * 接收到客端消息时触发
     * @param message
     * @param id
     */
    @OnMessage
    public void onMessage(String message, @PathParam("id") String id) {
        log.info("receive a message from client id={}, msg={}", id, message);
    }

    /**
     * 连接发生异常时触发
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Error while websocket.", error);
    }

    //向指定的客户端发送消息
    public void sendMessage(long id, String message){
        //根据id,从map中获取存储的webSocket对象
        WebSocketProcess webSocketProcess = concurrentHashMap.get(id);
        if (!ObjectUtils.isEmpty(webSocketProcess)) {
            //当客户端是Open状态时，才能发送消息
            if (webSocketProcess.session.isOpen()) {
                try {
                    //getBasicRemote获取客户端地址，sendText发送消息
                    webSocketProcess.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                log.error("webSocket session={} is closed", id);
            }
        } else {
            log.error("websocket session = {} is not exit", id);
        }
    }

    //向连接该服务器的所有客户端发送消息
    public void sendAllMessage(String msg) {
        log.info("online client count={}", concurrentHashMap.size());
        Set<Map.Entry<Long, WebSocketProcess>> entries = concurrentHashMap.entrySet();
        for (Map.Entry<Long, WebSocketProcess> entry : entries) {
            Long cid = entry.getKey();
            WebSocketProcess webSocketProcess = entry.getValue();
            boolean sessionOpen = webSocketProcess.session.isOpen();
            if (sessionOpen) {
                try {
                    webSocketProcess.session.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    log.info("多端发送消息异常");
                }
            }else {
                log.info("cid={} is closed, ignore send text", cid);
            }
        }
    }
}
