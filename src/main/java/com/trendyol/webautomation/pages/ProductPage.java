package com.trendyol.webautomation.pages;

import com.trendyol.webautomation.enums.ProductPageLocators;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver, Properties properties) {
        super(driver, properties);
    }

    public void clickAddProductToBasketButton() {
        ProductPageLocators addToBasketButton = ProductPageLocators.ADD_TO_BASKET_BUTTON;
        findElementToClick(addToBasketButton.getPath(), addToBasketButton.getPathType(), addToBasketButton.getTimeOut());
    }

}
