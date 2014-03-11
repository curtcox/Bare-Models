package net.baremodels.util;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
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
            System.out.println(properties);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream getResourceAsStream(String name) {
        try {
            File file = getResourceAsFile(name);
            return new FileInputStream(file);
        } catch (NullPointerException | URISyntaxException | FileNotFoundException e) {
            String message = "Unable to find : " + name;
            String className = "";
            String key = "";
            throw new MissingResourceException(message,className,key);
        }
    }

    private static File getResourceAsFile(String name) throws URISyntaxException {
        URL url = LivePropertiesFromSupplier.class.getResource(name);
        return new File(url.toURI());
    }

    public static String findResourceSource(String given) {
        return given
            .replaceAll("/target/classes/","/src/main/resources/")
            .replaceAll("/target/test-classes/","/src/test/resources/")
        ;
    }

}
