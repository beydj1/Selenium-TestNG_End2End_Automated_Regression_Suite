package com.automation.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(css = "span.title")
    private WebElement title;

    @FindBy(css = "div.inventory_item_name")
    private WebElement firstItemName;

    public String getPageTitle() {
        return readText(title);
    }

    public String getFirstItemName() {
        return readText(firstItemName);
    }
}
