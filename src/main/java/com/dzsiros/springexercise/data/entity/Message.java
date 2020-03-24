package com.dzsiros.springexercise.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    protected Message() {}

    public Message(String messageText) {
        this.messageText = messageText;
    }

    private String messageText;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Long getId() {
        return id;
    }
}
