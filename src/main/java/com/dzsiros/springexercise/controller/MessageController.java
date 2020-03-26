package com.dzsiros.springexercise.controller;

import com.dzsiros.springexercise.data.converter.MessageConverter;
import com.dzsiros.springexercise.data.entity.Message;
import com.dzsiros.springexercise.data.querydsl.MessageQuery;
import com.dzsiros.springexercise.data.repository.MessageRepository;
import com.dzsiros.springexercise.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    public static final String SESSION_SCOPE_MESSAGE_BEAN = "sessionScopeMessage";
    public static final String REQUEST_SCOPE_MESSAGE_BEAN = "requestScopeMessage";

    private final ApplicationContext context;
    private final MessageRepository messageRepository; // normally this should called in the service layer
    private final MessageConverter messageConverter;
    private final MessageQuery messageQuery;

    @Autowired
    public MessageController(ApplicationContext context, MessageRepository messageRepository, MessageConverter messageConverter, MessageQuery messageQuery) {
        this.context = context;
        this.messageRepository = messageRepository;
        this.messageConverter = messageConverter;
        this.messageQuery = messageQuery;
    }

    @PutMapping("/session-message")
    public String updateSessionScopeMessageText(@RequestParam String text) {
        MessageModel sessionScopeMessageModel = (MessageModel) context.getBean(SESSION_SCOPE_MESSAGE_BEAN);
        sessionScopeMessageModel.setMessageText(text);
        return "Session message text updated to: " + sessionScopeMessageModel.getMessageText();
    }

    @PutMapping("/request-message")
    public String updateRequestScopeMessageText(@RequestBody String text) {
        MessageModel requestScopeMessageModel = (MessageModel) context.getBean(REQUEST_SCOPE_MESSAGE_BEAN);
        requestScopeMessageModel.setMessageText(text);
        return "Request message text updated to: " + requestScopeMessageModel.getMessageText();
    }

    @GetMapping("/session-message")
    public String getSessionScopeMessage() {
        return  "Session message text is: " + ((MessageModel) context.getBean(SESSION_SCOPE_MESSAGE_BEAN)).getMessageText();
    }

    @GetMapping("/request-message")
    public String getRequestScopeMessage() {
        return "Request message text is: " + ((MessageModel) context.getBean(REQUEST_SCOPE_MESSAGE_BEAN)).getMessageText();
    }

    @PostMapping("/create")
    public Message createMessage(@RequestBody MessageModel messageModel) {
        return messageRepository.save(messageConverter.toEntity(messageModel));
    }

    @GetMapping("/repository/text")
    public List<Message> findByMessageTextLike(@RequestParam String text) {

        return messageRepository.findByMessageTextContains(text);
    }

    @GetMapping("/repository/pageable")
    public Page<Message> findAllPageableByQuery(@RequestParam int page,
                                                @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findAllPageableByQuery(pageable);
    }

    @GetMapping("/querydsl/text")
    public List<Message> findAllByMessageText(@RequestParam String text) {
        return messageQuery.findAllByMessageText(text);
    }

    @GetMapping("/querydsl/text-with-length")
    public List<Message> findAllByMessageTextWithLength(@RequestParam String text,
                                                        @RequestParam int length) {
        return messageQuery.findAllByMessageTextWithLength(text, length);
    }
}
