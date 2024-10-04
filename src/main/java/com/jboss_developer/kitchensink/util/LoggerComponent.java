package com.jboss_developer.kitchensink.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerComponent {

    private static final Logger logger = LoggerFactory.getLogger(LoggerComponent.class);

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logError(String message, Exception e) {
        logger.error(message, e);
    }

    public void logDebug(String message) {
        logger.debug(message);
    }
}
