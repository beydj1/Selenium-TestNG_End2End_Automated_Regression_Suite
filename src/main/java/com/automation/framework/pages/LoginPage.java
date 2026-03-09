package com.automation.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public LoginPage login(String username, String password) {
        enterText(usernameInput, username);
        enterText(passwordInput, password);
        click(loginButton);
        return this;
    }

    public String getErrorMessage() {
        return readText(errorMessage);
    }
}
