package com.kh.sbilyhour.common_module.domain.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T jsonStringToObject(String jsonString, Class<T> clazz) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(jsonString, clazz);
        } catch (Exception e) {
            logger.error("Error converting JSON string to object", e);
            return null;
        }
    }
}
