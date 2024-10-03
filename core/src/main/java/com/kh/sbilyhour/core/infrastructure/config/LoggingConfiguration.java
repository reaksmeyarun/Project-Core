
package com.kh.sbilyhour.core.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up logging.
 * This class provides a bean for a logger used across the application.
 */
@Configuration
public class LoggingConfiguration {

    /**
     * Creates and provides a Logger instance for the application.
     * <p>
     * This method initializes a logger with the name "GlobalExceptionHandler".
     * This logger can be used to log messages related to exception handling
     * throughout the application.
     *
     * @return a Logger instance for the specified logger name
     */
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("GlobalExceptionHandler");
    }

}