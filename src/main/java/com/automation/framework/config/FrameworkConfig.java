package com.automation.framework.config;

import java.time.Duration;
import java.util.Properties;

public final class FrameworkConfig {

    private static final FrameworkConfig INSTANCE = new FrameworkConfig();

    private final Properties envProperties;
    private final Properties dataProperties;

    private FrameworkConfig() {
        this.envProperties = PropertiesLoader.load("env.properties", "env.file");
        this.dataProperties = PropertiesLoader.load("data.properties", "data.file");
    }

    public static FrameworkConfig getInstance() {
        return INSTANCE;
    }

    public String getEnv(String key) {
        return envProperties.getProperty(key);
    }

    public String getEnv(String key, String defaultValue) {
        return envProperties.getProperty(key, defaultValue);
    }

    public String getData(String key) {
        return dataProperties.getProperty(key);
    }

    public Duration getImplicitWait() {
        return Duration.ofSeconds(Long.parseLong(getEnv("implicit.wait.seconds", "5")));
    }

    public Duration getExplicitWait() {
        return Duration.ofSeconds(Long.parseLong(getEnv("explicit.wait.seconds", "10")));
    }

    public String getBaseUrl() {
        return getEnv("base.url");
    }

    public String getBrowser() {
        return getEnv("browser", "chrome").toLowerCase();
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getEnv("headless", "true"));
    }
}
