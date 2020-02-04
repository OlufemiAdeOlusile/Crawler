package com.web.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyLoader.class);

    private PropertyLoader() {
    }

    public static String loadResource(String file) {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(ResourceLoader.class.getClassLoader());
            return resolver.getResource(file).getFile().getPath();
        } catch (IOException e) {
            LOGGER.error("Exception while parsing conditions", e);
            throw new IllegalStateException("Conditions can't be parsed");
        }
    }

    public static Properties retrieveProperty(String path) {
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(path);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Cannot Read From Config", e);
        }
        return properties;
    }
}
