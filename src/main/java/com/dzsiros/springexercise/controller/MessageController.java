package com.dzsiros.springexercise.controller;

import com.dzsiros.springexercise.data.converter.MessageConverter;
import com.dzsiros.springexercise.data.entity.Message;
import com.dzsiros.springexercise.data.querydsl.MessageQuery;
import com.dzsiros.springexercise.data.repository.MessageRepository;
import com.dzsiros.springexercise.model.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import static com.dzsiros.springexercise.config.MessageConfig.REQUEST_SCOPE_MESSAGE_BEAN_QUALIFIER;
import static com.dzsiros.springexercise.config.MessageConfig.SESSION_SCOPE_MESSAGE_BEAN_QUALIFIER;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    private final MessageRepository messageRepository; // normally this should called in the service layer
    private final MessageConverter messageConverter;
    private final MessageQuery messageQuery;
    private final MessageModel sessionScopeMessage;
    private final MessageModel requestScopeMessage;
    private final ApplicationContext applicationContext;

    @Autowired
    public MessageController(MessageRepository messageRepository,
                             MessageConverter messageConverter,
                             MessageQuery messageQuery,
                             @Qualifier(SESSION_SCOPE_MESSAGE_BEAN_QUALIFIER) MessageModel sessionScopeMessage,
                             @Qualifier(REQUEST_SCOPE_MESSAGE_BEAN_QUALIFIER) MessageModel requestScopeMessage,
                             ApplicationContext applicationContext) {
        this.messageRepository = messageRepository;
        this.messageConverter = messageConverter;
        this.messageQuery = messageQuery;
        this.sessionScopeMessage = sessionScopeMessage;
        this.requestScopeMessage = requestScopeMessage;
        this.applicationContext = applicationContext;
    }

    @PutMapping("/session-message")
    public String updateSessionScopeMessageText(@RequestParam String text) {
        sessionScopeMessage.setMessageText(text);
        String updatedText = ((MessageModel) applicationContext.getBean(SESSION_SCOPE_MESSAGE_BEAN_QUALIFIER)).getMessageText(); // request new instance
        log.info("Session scope bean text was set to: " + updatedText);
        return "Session message text updated to: " + sessionScopeMessage.getMessageText();
    }

    @PutMapping("/request-message")
    public String updateRequestScopeMessageText(@RequestParam String text) {
        requestScopeMessage.setMessageText(text);
        String updatedText = ((MessageModel) applicationContext.getBean(REQUEST_SCOPE_MESSAGE_BEAN_QUALIFIER)).getMessageText(); // request new instance
        log.info("Session scope bean text was set to: " + updatedText);
        return "Request message text updated to: " + requestScopeMessage.getMessageText();
    }

    @GetMapping("/session-message")
    public String getSessionScopeMessage() {
        return "Session message text is: " + sessionScopeMessage.getMessageText();
    }

    @GetMapping("/request-message")
    public String getRequestScopeMessage() {
        return "Request message text is: " + requestScopeMessage.getMessageText();
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
