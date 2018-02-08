package com.thoughtworks.felix.support;

import org.springframework.context.ApplicationContext;

public final class SpringContextUtil {
    private static ApplicationContext applicationContext;

    public SpringContextUtil() {
        super();
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBeann(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
