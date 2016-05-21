package com.tranetech.openspace.nanochat;

/**
 * Created by Android Developer on 11-Apr-16.
 */
public class ChatMessage {
    private String name;
    private String text;

    public ChatMessage() {
        // necessary for Firebase's deserializer
    }
    public ChatMessage(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
