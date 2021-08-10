package com.trendyol.webautomation.utils;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class StepUtils {

    private StepUtils() {
    }

    public static void readLog4jProperties() {
        try {
            Properties props = new Properties();
            props.load(StepUtils.class.getResourceAsStream("/log4j.properties"));
            PropertyConfigurator.configure(props);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Properties getProperties() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("data.properties");
            properties.load(inputStream);
            return properties;
        } catch (Exception exception) {
            throw new RuntimeException("Properties file could not be read");
        }
    }

    public static WebDriver getWebDriver() {
        Properties properties = getProperties();
        String browserName = properties.getProperty("browser");
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("driverPath"));
            return new ChromeDriver();
        } else if (browserName.equals("IE")) {
            System.setProperty("webdriver.geckho.driver", properties.getProperty("driverPath"));
            return new InternetExplorerDriver();
        } else {
            throw new RuntimeException("Driver type not found");
        }
    }
}
