package com.dzsiros.springexercise.config;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@PropertySource("classpath:dev.properties")
public class DevPropertyConfig {
}
