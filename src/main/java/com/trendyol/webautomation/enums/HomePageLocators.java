package com.trendyol.webautomation.enums;

public enum HomePageLocators {

    CATEGORIES("/html/body/div[1]/div[2]/div/div/div[1]/nav/ul/li", PathType.X_PATH, TimeOut.FIVE_SECONDS),
    PRODUCTS("boutique-product", PathType.CLASS_NAME, TimeOut.FIVE_SECONDS),
    BASKET_PAGE_BUTTON("account-basket", PathType.CLASS_NAME, TimeOut.FIVE_SECONDS);


    private final String path;
    private final PathType pathType;
    private final TimeOut timeOut;


    HomePageLocators(String path, PathType pathType, TimeOut timeOut) {
        this.path = path;
        this.pathType = pathType;
        this.timeOut = timeOut;
    }

    public String getPath() {
        return path;
    }

    public PathType getPathType() {
        return pathType;
    }

    public TimeOut getTimeOut() {
        return timeOut;
    }
}
