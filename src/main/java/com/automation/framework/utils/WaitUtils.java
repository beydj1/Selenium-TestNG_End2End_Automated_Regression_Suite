package com.automation.framework.utils;

import com.automation.framework.config.FrameworkConfig;
import com.automation.framework.core.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitUtils {

    private WaitUtils() {
    }

    public static WebElement untilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(
                DriverFactory.getDriver(),
                FrameworkConfig.getInstance().getExplicitWait()
        );
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement untilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(
                DriverFactory.getDriver(),
                FrameworkConfig.getInstance().getExplicitWait()
        );
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
