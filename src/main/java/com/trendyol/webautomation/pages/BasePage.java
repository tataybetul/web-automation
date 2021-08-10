package com.trendyol.webautomation.pages;

import com.trendyol.webautomation.enums.PathType;
import com.trendyol.webautomation.enums.TimeOut;
import com.trendyol.webautomation.utils.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.security.PublicKey;
import java.util.Properties;

public abstract class BasePage {

    protected WebDriver driver;
    protected Properties properties;
    protected WebDriverWait wait;

    protected static final Logger logger = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
        this.wait = new WebDriverWait(driver, 30);
    }

    //public abstract void validateForPageToOpen();

    public void findElementToClick(String path, PathType type, TimeOut timeOut) {
        WebElement element = findElement(path, type, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public WebElement findElement(String path, PathType type, TimeOut timeOut) {
        try {
            WebElement element;
            switch (type) {
                case CLASS_NAME:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(path)));
                    break;
                case ID:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(path)));
                    break;
                case NAME:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(path)));
                    break;
                case X_PATH:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
                    break;
                case CSS_SELECTOR:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(path)));
                    break;
                case LINK_TEST:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(path)));
                    break;
                case TAG:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(path)));
                default:
                    throw new RuntimeException("Path type not found!");
            }
            return element;
        } catch (Exception ex) {
            return null;
        }
    }

    public void downPage(long length) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(String.format("window.scrollBy(0,%d)", length), "");
    }

    public void slowlyDownPage(long length) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        var i = 0;
        do {
            jse.executeScript(String.format("window.scrollBy(0, %d)", 100), "");
            PageUtils.sleep(100);
            i+=100;
        } while (i < length);
    }

    public void slowlyDownPageAndWait(long length, long millis) {
        slowlyDownPage(length);
        waitForLoad();
        PageUtils.sleep(millis);
    }

    public void downPageAndWait(long length, long millis) {
        downPage(length);
        waitForLoad();
        PageUtils.sleep(millis);
    }

    public void upPage(long length) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(String.format("window.scrollBy(0,-%d)", length), "");
    }

    public void upPageAndWait(long length, long millis) {
        upPage(length);
        waitForLoad();
        PageUtils.sleep(millis);
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        wait.until(pageLoadCondition);
    }
}
