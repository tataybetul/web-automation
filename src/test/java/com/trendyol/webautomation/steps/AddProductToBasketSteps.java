package com.trendyol.webautomation.steps;

import com.trendyol.webautomation.pages.HomePage;
import com.trendyol.webautomation.pages.LoginPage;
import com.trendyol.webautomation.pages.ProductPage;
import com.trendyol.webautomation.utils.PageUtils;
import com.trendyol.webautomation.utils.StepUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class AddProductToBasketSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductPage productPage;

    @Before
    public void initialize() {
        StepUtils.readLog4jProperties();
        Properties properties = StepUtils.getProperties();
        WebDriver driver = StepUtils.getWebDriver();
        this.driver = driver;
        this.loginPage = new LoginPage(driver, properties);
        this.homePage = new HomePage(driver, properties);
        this.productPage = new ProductPage(driver, properties);
    }

    @Given("it should open login page")
    public void it_should_open_login_page(){
        loginPage.open();
    }

    @Given("it should scale full window")
    public void it_should_scale_full_window() {
        driver.manage().window().maximize();
    }

    @When("^it should login by email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void it_should_login(String email, String password) {
        loginPage.login(email, password);
        PageUtils.sleep(1000);
    }

    @Then("it should log when butiks has dead image")
    public void it_should_log_when_butiks_has_dead_image(){
        homePage.logHasDeadImageButiks();
    }

    @When("it should click random category")
    public void it_should_click_random_category() {
        homePage.clickRandomCategory();
        PageUtils.sleep(1000);
    }

    @When("it should click random butik")
    public void it_should_click_random_butik() {
        homePage.clickRandomButik();
        PageUtils.sleep(1000);
    }

    @Then("it should log when products has dead image")
    public void it_should_log_when_producs_has_dead_image(){
        homePage.logHasDeadImageProducts();
    }

    @When("it should click random product")
    public void it_should_click_random_product() {
        homePage.clickRandomProduct();
        PageUtils.sleep(1000);
    }

    @When("it should add product to basket")
    public void it_should_add_product_to_basket() {
        productPage.clickAddProductToBasketButton();
        PageUtils.sleep(2000);
    }

    @When("it should open basket page")
    public void it_should_open_basket_page() {
        homePage.openBasketPage();
        PageUtils.sleep(3000);
    }

    @After
    public void quit() {
        driver.quit();
    }
}
