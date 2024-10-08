package com.kh.sbilyhour.common_module.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeConfig {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    /**
     * Provides the current date and time formatted as a string.
     *
     * @return the formatted current date and time
     */
    @Bean
    public String currentFormattedDate() {
        return OffsetDateTime.now().format(FORMATTER);
    }

}
