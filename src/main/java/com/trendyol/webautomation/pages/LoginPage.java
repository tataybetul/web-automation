package com.trendyol.webautomation.pages;

import com.trendyol.webautomation.enums.LoginPageLocators;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;

public class LoginPage extends BasePage {

    private final String url;

    public LoginPage(WebDriver driver, Properties properties) {
        super(driver, properties);
        this.url = properties.getProperty("loginPageUrl");
    }

    public void open() {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(findElementByLocator(LoginPageLocators.LOGIN_FORM)));
    }

    public void login(String email, String password) {

        Assert.assertNotNull(email);
        Assert.assertNotNull(password);
        sendEmail(email);
        sendPassword(password);
        clickLogin();
    }

    private void sendEmail(String email) {
        findElementByLocator(LoginPageLocators.EMAIL_TEXT).sendKeys(email);
    }

    private void sendPassword(String password){
        findElementByLocator(LoginPageLocators.PASSWORD_TEXT).sendKeys(password);
    }

    private void clickLogin(){
        findElementByLocator(LoginPageLocators.LOGIN_BUTTON).submit();
    }

    private WebElement findElementByLocator(LoginPageLocators locators) {
        return findElement(locators.getPath(), locators.getPathType(), locators.getTimeOut());
    }
}
