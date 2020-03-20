package com.dzsiros.springexercise.config;

import com.dzsiros.springexercise.filter.SessionMessageFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean <SessionMessageFilter> filterRegistrationBean() {
        FilterRegistrationBean <SessionMessageFilter> registrationBean = new FilterRegistrationBean<>();
        SessionMessageFilter customURLFilter = new SessionMessageFilter();
        registrationBean.setFilter(customURLFilter);
        registrationBean.addUrlPatterns("/api/v1/message/session-message/*");
        return registrationBean;
    }
}
