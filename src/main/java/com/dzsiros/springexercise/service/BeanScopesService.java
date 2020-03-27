package com.dzsiros.springexercise.service;

import com.dzsiros.springexercise.model.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.dzsiros.springexercise.config.MessageConfig.PROTOTYPE_SCOPE_MESSAGE_BEAN_QUALIFIER;
import static com.dzsiros.springexercise.config.MessageConfig.SINGLETON_SCOPE_MESSAGE_BEAN_QUALIFIER;

@Service
public class BeanScopesService implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BeanScopesService.class);

    private final Environment environment;
    private final MessageModel singletonMessageModel;
    private final MessageModel prototypeMessageModel;
    private final ApplicationContext applicationContext;

    @Autowired
    public BeanScopesService(Environment environment,
                             @Qualifier(SINGLETON_SCOPE_MESSAGE_BEAN_QUALIFIER) MessageModel singletonMessageModel,
                             @Qualifier(PROTOTYPE_SCOPE_MESSAGE_BEAN_QUALIFIER) MessageModel prototypeMessageModel, ApplicationContext applicationContext) {
        this.environment = environment;
        this.singletonMessageModel = singletonMessageModel;
        this.prototypeMessageModel = prototypeMessageModel;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {

        // print active profiles
        Arrays.stream(environment.getActiveProfiles())
                .forEach(profileName -> log.info("Currently active profile - " + profileName));

        String customProperty = environment.getProperty("foo.custom.property");
        String otherCustomProperty = environment.getProperty("foo.custom.other.property");

        log.info(customProperty);
        log.info(otherCustomProperty);

        demonstrateBeanScopes();
    }

    private void demonstrateBeanScopes() {

        log.info("------");
        log.info("Set singletonMessage's text to : 'singleton text'");
        singletonMessageModel.setMessageText("singleton text");

        log.info("Get the object again from the context and print its text");
        log.info(((MessageModel) applicationContext.getBean(SINGLETON_SCOPE_MESSAGE_BEAN_QUALIFIER)).getMessageText());

        log.info("------");
        log.info("Set prototypeMessage's text to: 'prototype text'");
        prototypeMessageModel.setMessageText("prototype text");

        log.info("Get the object again from the context and print its text");
        log.info(((MessageModel) applicationContext.getBean(PROTOTYPE_SCOPE_MESSAGE_BEAN_QUALIFIER)).getMessageText());
    }

}
