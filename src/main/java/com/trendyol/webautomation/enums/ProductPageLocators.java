package com.trendyol.webautomation.enums;

public enum ProductPageLocators {

    PRODUCT_CONTAINER("product-container", PathType.CLASS_NAME, TimeOut.FIVE_SECONDS),
    PRODUCT_IMAGE_CONTAINER("base-product-image", PathType.CLASS_NAME, TimeOut.FIVE_SECONDS),
    ADD_TO_BASKET_BUTTON("add-to-basket", PathType.CLASS_NAME, TimeOut.FIVE_SECONDS),
    ;

    private final String path;
    private final PathType pathType;
    private final TimeOut timeOut;

    ProductPageLocators(String path, PathType pathType, TimeOut timeOut) {
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
