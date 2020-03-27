package com.dzsiros.springexercise.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionMessageFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SessionMessageFilter.class);
    public static final String ERROR_MESSAGE = "Message must not be null or empty";
    public static final String TEXT_PARAM_NAME = "text";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        log.info("Filter request");
        log.info("Remote host: " + request.getRemoteHost());
        log.info("Remote address: " + request.getRemoteAddr());
        log.info("Remote port: " + request.getRemotePort());

        if (HttpMethod.PUT.name().equals(request.getMethod())) {
            String message = request.getParameter(TEXT_PARAM_NAME).trim();
            if (message.isEmpty()) {
                log.error(ERROR_MESSAGE);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_MESSAGE);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
