package com.automation.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class PropertiesLoader {

    private PropertiesLoader() {
    }

    public static Properties load(String fileName, String systemPropertyKey) {
        String externalFilePath = System.getProperty(systemPropertyKey);
        if (externalFilePath != null && !externalFilePath.isBlank()) {
            return loadFromPath(Paths.get(externalFilePath));
        }

        try (InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalStateException("Unable to locate properties file in classpath: " + fileName);
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to load properties file: " + fileName, exception);
        }
    }

    private static Properties loadFromPath(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalStateException("Properties file does not exist: " + path);
        }

        try (InputStream inputStream = Files.newInputStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to load properties file from path: " + path, exception);
        }
    }
}
