package com.automation.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    @FindBy(css = "span.title")
    private WebElement title;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpackAddToCartButton;

    @FindBy(css = "a.shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = "span.shopping_cart_badge")
    private WebElement cartBadge;

    public String getPageTitle() {
        return readText(title);
    }

    public InventoryPage addBackpackToCart() {
        click(backpackAddToCartButton);
        return this;
    }

    public String getCartCount() {
        return readText(cartBadge);
    }

    public CartPage goToCart() {
        click(cartIcon);
        return new CartPage();
    }
}
