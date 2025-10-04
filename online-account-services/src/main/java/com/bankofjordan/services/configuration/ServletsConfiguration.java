package com.bankofjordan.services.configuration;

import com.bankofjordan.services.servlets.HelloWorldServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ServletsConfiguration {

    @Bean
    public ServletRegistrationBean<HelloWorldServlet> servletRegistrationBean() {
        ServletRegistrationBean<HelloWorldServlet> bean = new ServletRegistrationBean<>(new HelloWorldServlet());
        bean.setUrlMappings(Arrays.asList("/welcome"));
        return bean;
    }
}
