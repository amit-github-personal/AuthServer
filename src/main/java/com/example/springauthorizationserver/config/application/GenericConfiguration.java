package com.example.springauthorizationserver.config.application;

import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class GenericConfiguration {

    @Bean
    SpringResourceTemplateResolver defaultTemplateResolver(ApplicationContext context) {
        var templateResolver = new SpringResourceTemplateResolver();
        var properties = new ThymeleafProperties();
        templateResolver.setApplicationContext(context);
        templateResolver.setPrefix("classpath:/static/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        if (properties.getEncoding() != null) {
            templateResolver.setCharacterEncoding(properties.getEncoding().name());
        }

        templateResolver.setCacheable(properties.isCache());
        Integer order = properties.getTemplateResolverOrder();
        if (order != null) {
            templateResolver.setOrder(order);
        }

        templateResolver.setCheckExistence(properties.isCheckTemplate());
        return templateResolver;
    }
}
