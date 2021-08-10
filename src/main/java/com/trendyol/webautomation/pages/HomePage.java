package com.trendyol.webautomation.pages;

import com.trendyol.webautomation.enums.HomePageLocators;
import com.trendyol.webautomation.utils.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    private Random random = new Random();

    public HomePage(WebDriver driver, Properties properties) {
        super(driver, properties);
    }

    public void logHasDeadImageButiks() {
        List<Category> categories = getCategories();
        if (categories.isEmpty()) {
            throw new RuntimeException("Categories not found.");
        }
        for (Category category : categories) {
            driver.get(category.url);
            List<Butik> butiks = getButikBigBannerList();
            if (butiks.isEmpty()) {
                logger.warn(String.format("%s ait butik bulnamadı.", category.getName()));
                continue;
            }
            for (Butik butik : butiks) {
                boolean existsImage = PageUtils.existsImage(butik.getImageUrl());
                if (!existsImage) {
                    logger.error(String.format("Butik image not found. Category Name: %s, Url: %s", category.getName(), butik.getImageUrl()));
                } else {
                    logger.info(String.format("Butik image validated. Category Name: %s, Url: %s", category.getName(), butik.getImageUrl()));
                }
            }
        }
    }

    public void clickRandomCategory() {
        Category category = getRandomCategory();
        driver.get(category.url);
        waitForLoad();
    }

    public void clickRandomButik() {
        Butik butik = getRandomButik();
        driver.get(butik.getUrl());
        waitForLoad();
    }

    public void logHasDeadImageProducts() {
        List<Product> products = getProducts();
        if (products.isEmpty()) {
            throw new RuntimeException("Product not found.");
        }
        for (Product product : products) {
            boolean existsImage = PageUtils.existsImage(product.getImgUrl());
            if (!existsImage) {
                logger.error(String.format("Product image not found. Product Id: %s, Url: %s", product.getId(), product.getImgUrl()));
            } else {
                logger.info(String.format("Product image validated. Product Id: %s, Url: %s", product.getId(), product.getImgUrl()));
            }
        }
    }

    public void clickRandomProduct() {
        Product product = getRandomProduct();
        driver.get(product.getUrl());
        waitForLoad();
    }

    public void openBasketPage() {
        findElementToClickByLocator(HomePageLocators.BASKET_PAGE_BUTTON);
    }

    private Category getRandomCategory() {
        List<Category> categories = getCategories();
        if (categories.isEmpty()) {
            throw new RuntimeException("Category not found");
        }
        int index = random.nextInt(categories.size());
        return categories.get(index);
    }

    private Butik getRandomButik() {
        List<Butik> butiks = getButikBigBannerList();
        if (butiks.isEmpty()) {
            throw new RuntimeException("Butik not found.");
        }
        int index = random.nextInt(butiks.size());
        return butiks.get(index);
    }

    private Product getRandomProduct() {
        List<Product> products = getProducts();
        if (products.isEmpty()) {
            throw new RuntimeException("Product not found.");
        }
        int index = random.nextInt(products.size());
        return products.get(index);
    }

    private List<Category> getCategories() {
        List<WebElement> categoryElements = driver.findElements(By.xpath(HomePageLocators.CATEGORIES.getPath()));
        if (categoryElements.isEmpty()) {
            return Collections.emptyList();
        }
        return categoryElements.stream().map(e -> {
            String tabName = e.getText();
            String url = e.findElement(By.tagName("a")).getAttribute("href");
            return new Category(url, tabName);
        }).collect(Collectors.toList());
    }

    private List<Butik> getButiks() { // Big Banner and small banner
        slowlyDownPageAndWait(41000, 1000);
        upPageAndWait(41000, 1000);
        var homePageWrapperElement = driver.findElement(By.id("browsing-gw-homepage"));
        var articleElements = homePageWrapperElement.findElements(By.tagName("article"));
        return getButiksByArticleElements(articleElements);
    }

    private List<Butik> getButikBigBannerList() {
        slowlyDownPageAndWait(6000, 1000);
        upPageAndWait(6000, 1000);
        var homePageWrapperElement = driver.findElement(By.id("browsing-gw-homepage"));
        var bigBannersElement = homePageWrapperElement.findElement(By.className("component-big-list"));
        var articleElements = bigBannersElement.findElements(By.tagName("article"));
        return getButiksByArticleElements(articleElements);
    }

    private List<Butik> getButikSmallBannerList() {
        downPageAndWait(41000, 2000);
        upPageAndWait(41000, 2000);
        var homePageWrapperElement = driver.findElement(By.id("browsing-gw-homepage"));
        var bigBannersElement = homePageWrapperElement.findElement(By.className( "component-small-list"));
        var articleElements = bigBannersElement.findElements(By.tagName("article"));
        return getButiksByArticleElements(articleElements);
    }

    private List<Product> getProducts() {
        slowlyDownPageAndWait(2000, 2000);
        upPageAndWait(2000, 2000);
        List<WebElement> productElements = driver.findElements(By.className("boutique-product"));
        if (productElements.isEmpty()) {
            productElements = driver.findElements(By.className("p-card-wrppr"));
            if (productElements.isEmpty()) {
                return Collections.emptyList();
            }
        }
        return productElements.stream().map(e -> {
            String productId = e.getAttribute("data-id");
            String url = e.findElement(By.tagName("a")).getAttribute("href");
            String imgUrl =  e.findElement(By.className("image-container")).findElement(By.tagName("img")).getAttribute("src");
            return new Product(productId, url, imgUrl);
        }).collect(Collectors.toList());
    }

    private List<Butik> getButiksByArticleElements(List<WebElement> articleElements) {
        return articleElements.stream().map(articleElement -> {
            String name = articleElement.findElement(By.tagName("summary")).findElement(By.tagName("span")).getAttribute("name");
            String url = articleElement.findElement(By.tagName("a")).getAttribute("href");
            WebElement imgElement = articleElement.findElement(By.tagName("img"));
            String imgUrl = imgElement.getAttribute("src");
            return new Butik(url, imgUrl, name);
        }).collect(Collectors.toList());
    }

    private void findElementToClickByLocator(HomePageLocators locator){
        findElementToClick(locator.getPath(),locator.getPathType(),locator.getTimeOut());
    }

    public static class Butik {

        private final String url;
        private final String imageUrl;
        private final String name;

        public Butik(String url, String imageUrl, String name) {
            this.url = url;
            this.imageUrl = imageUrl;
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getName() {
            return name;
        }
    }

    public static class Category {

        private final String url;
        private final String name;

        public Category(String url, String name) {
            this.url = url;
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }
    }

    private static class Product {

        private final String id;
        private final String url;
        private final String imgUrl;

        private Product(String productId, String url, String imgUrl) {
            this.id = productId;
            this.url = url;
            this.imgUrl = imgUrl;
        }

        public String getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        public String getImgUrl() {
            return imgUrl;
        }
    }
}
