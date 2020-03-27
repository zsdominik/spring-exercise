package com.dzsiros.springexercise.config;

import com.dzsiros.springexercise.model.MessageModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class MessageConfig {

    public static final String REQUEST_SCOPE_MESSAGE_BEAN_QUALIFIER = "requestScopeMessage";
    public static final String SESSION_SCOPE_MESSAGE_BEAN_QUALIFIER = "sessionScopeMessage";
    public static final String SINGLETON_SCOPE_MESSAGE_BEAN_QUALIFIER = "singletonScopeMessage";
    public static final String PROTOTYPE_SCOPE_MESSAGE_BEAN_QUALIFIER = "prototypeScopeMessage";

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
    public MessageModel singletonScopeMessage() {
        return new MessageModel();
    }
}
