package com.example.springauthorizationserver.config.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class DispatcherServletConfig {
//
//    @Bean
//    DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }
//
//    @Bean
//    @ConditionalOnBean(value = {DispatcherServlet.class})
//    ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/api/");
//        servletRegistrationBean.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
//        return servletRegistrationBean;
//    }


}
