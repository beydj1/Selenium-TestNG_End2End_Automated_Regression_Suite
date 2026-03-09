package com.automation.framework.pages;

import com.automation.framework.core.DriverFactory;
import com.automation.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    protected void enterText(WebElement element, String value) {
        WaitUtils.untilVisible(element).clear();
        element.sendKeys(value);
    }

    protected void click(WebElement element) {
        WaitUtils.untilClickable(element).click();
    }

    protected String readText(WebElement element) {
        return WaitUtils.untilVisible(element).getText();
    }
}
