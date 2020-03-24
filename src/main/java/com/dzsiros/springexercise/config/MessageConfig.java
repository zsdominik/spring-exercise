package com.dzsiros.springexercise.config;

import com.dzsiros.springexercise.model.MessageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class MessageConfig {

    @Bean
    @RequestScope
    public MessageModel requestScopeMessage() {
        return new MessageModel();
    }

    @Bean
    @SessionScope
    public MessageModel sessionScopeMessage() {
        return new MessageModel();
    }

    @Bean
    @Scope("prototype")
    public MessageModel prototypeScopeMessage() {
        return new MessageModel();
    }

    @Bean
    public MessageModel singletonMessage() {
        return new MessageModel();
    }
}
