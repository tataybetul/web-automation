package com.trendyol.webautomation.enums;

public enum LoginPageLocators {

    LOGIN_FORM("//*[@id=\"login-register\"]/div[3]/div[1]/form", PathType.X_PATH, TimeOut.FIVE_SECONDS),
    EMAIL_TEXT("login-email", PathType.ID, TimeOut.FIVE_SECONDS),
    PASSWORD_TEXT("login-password-input",PathType.ID, TimeOut.FIVE_SECONDS),
    LOGIN_BUTTON("/html/body/div[3]/div/div[3]/div[1]/form/button", PathType.X_PATH, TimeOut.FIVE_SECONDS);


    private final String path;
    private final PathType pathType;
    private final TimeOut timeOut;

    LoginPageLocators(String path, PathType pathType, TimeOut timeOut) {
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
