package com.automation.framework.core;

import com.automation.framework.config.FrameworkConfig;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER_HOLDER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initializeDriver() {
        FrameworkConfig config = FrameworkConfig.getInstance();
        WebDriver driver = createDriver(config.getBrowser(), config.isHeadless());
        driver.manage().timeouts().implicitlyWait(config.getImplicitWait());
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        DRIVER_HOLDER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER_HOLDER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call initializeDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER_HOLDER.get();
        if (driver != null) {
            driver.quit();
            DRIVER_HOLDER.remove();
        }
    }

    private static WebDriver createDriver(String browser, boolean headless) {
        return switch (browser) {
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("-headless");
                }
                yield new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                yield new EdgeDriver(options);
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                if (headless) {
                    options.addArguments("--headless=new");
                }
                yield new ChromeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
