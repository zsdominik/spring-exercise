package com.dzsiros.springexercise.config;

import com.dzsiros.springexercise.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class MessageConfig {

    @Bean
    @RequestScope
    public Message requestScopeMessage() {
        return new Message();
    }

    @Bean
    @SessionScope
    public Message sessionScopeMessage() {
        return new Message();
    }

    @Bean
    @Scope("prototype")
    public Message prototypeScopeMessage() {
        return new Message();
    }

    @Bean
    public Message singletonMessage() {
        return new Message();
    }
}
