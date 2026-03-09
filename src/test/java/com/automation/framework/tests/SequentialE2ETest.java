package com.automation.framework.tests;

import com.automation.framework.config.FrameworkConfig;
import com.automation.framework.pages.CartPage;
import com.automation.framework.pages.InventoryPage;
import com.automation.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SequentialE2ETest extends BaseTest {

    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @Test(priority = 1)
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(
                FrameworkConfig.getInstance().getData("standard.username"),
                FrameworkConfig.getInstance().getData("standard.password")
        );

        inventoryPage = new InventoryPage();
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "User should land on inventory page after login.");
    }

    @Test(priority = 2, dependsOnMethods = "loginWithValidCredentials")
    public void addItemToCartSequentially() {
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartCount(), "1", "Cart badge count should be updated.");
    }

    @Test(priority = 3, dependsOnMethods = "addItemToCartSequentially")
    public void verifyItemInCartSequentially() {
        cartPage = inventoryPage.goToCart();
        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart", "Cart page title should be visible.");
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack", "Added product should be present in the cart.");
    }
}
