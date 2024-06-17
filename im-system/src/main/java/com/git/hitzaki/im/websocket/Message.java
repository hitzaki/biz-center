package com.git.hitzaki.im.websocket;
/**
 * TODO
 * @author hitzaki
 */
public class Message {
    private String name;
    private String message;

    public Message(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
