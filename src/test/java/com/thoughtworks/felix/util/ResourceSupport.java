package com.thoughtworks.felix.util;

import com.thoughtworks.felix.service.ResourceAdvice;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public abstract class ResourceSupport {

    private HandlerExceptionResolver globalExceptionHandlerResolver;

    public ResourceSupport() {
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler", ResourceAdvice.class);

        WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);

        globalExceptionHandlerResolver = webMvcConfigurationSupport.handlerExceptionResolver();
    }

    protected HandlerExceptionResolver getGlobalExceptionHandlerResolver() {
        return globalExceptionHandlerResolver;
    }
}
