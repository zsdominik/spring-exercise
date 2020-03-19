package com.dzsiros.springexercise.controller;

import com.dzsiros.springexercise.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    public static final String SESSION_SCOPE_MESSAGE_BEAN = "sessionScopeMessage";
    public static final String REQUEST_SCOPE_MESSAGE_BEAN = "requestScopeMessage";
    @Autowired
    private ApplicationContext context;

    @PutMapping("/session-message")
    public Message updateSessionScopeMessageText(@RequestBody String text) {
        Message sessionScopeMessage = (Message) context.getBean(SESSION_SCOPE_MESSAGE_BEAN);
        sessionScopeMessage.setMessageText(text);
        return sessionScopeMessage;
    }

    @PutMapping("/request-message")
    public Message updateRequestScopeMessageText(@RequestBody String text) {
        Message requestScopeMessage = (Message) context.getBean(REQUEST_SCOPE_MESSAGE_BEAN);
        requestScopeMessage.setMessageText(text);
        return requestScopeMessage;
    }

    @GetMapping("/session-message")
    public Message getSessionScopeMessage() {
        return (Message) context.getBean(SESSION_SCOPE_MESSAGE_BEAN);
    }

    @GetMapping("/request-message")
    public Message getRequestScopeMessage() {
        return (Message) context.getBean(REQUEST_SCOPE_MESSAGE_BEAN);
    }
}
