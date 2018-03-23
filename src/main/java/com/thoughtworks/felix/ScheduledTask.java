package com.thoughtworks.felix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTask {

    private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);


    @Scheduled(fixedRate = 5000)
    public static void reportCurrentTime() {
        LOGGER.info("[A] Starting new cycle of scheduled task");
        final long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime <= 5000);
        LOGGER.info("现在时间: " + DATA_FORMAT.format(new Date()));
        LOGGER.info("[A] Done the cycle of scheduled task");
    }

    @Scheduled(fixedRate = 5000)
    public static void reportCurrentTime2() {
        LOGGER.info("[B] Starting new cycle of scheduled task");
        final long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime <= 5000);
        LOGGER.info("现在时间: " + DATA_FORMAT.format(new Date()));
        LOGGER.info("[B] Done the cycle of scheduled task");
    }
}
