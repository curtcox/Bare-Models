package net.baremodels.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * Static methods for working with resource files.
 */
public final class ResourceUtil {

    public static Properties properties(String name) {
        try (InputStream inputStream = getResourceAsStream(name);) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream getResourceAsStream(String name) {
        InputStream inputStream = LivePropertiesFromSupplier.class.getResourceAsStream(name);
        if (inputStream==null) {
            String message = "Unable to find : " + name;
            String className = "";
            String key = "";
            throw new MissingResourceException(message,className,key);
        }
        return inputStream;
    }

}
