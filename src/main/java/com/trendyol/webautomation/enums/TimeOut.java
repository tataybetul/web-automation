package com.trendyol.webautomation.enums;

public enum TimeOut {

    FIVE_SECONDS(5),
    TEN_SECONDS(10),
    FIFTEEN_SECONDS(15),
    SIXTEN_SECONDS(60);

    private final int value;

    public int getValue() {
        return value;
    }

    TimeOut(int value) {
        this.value = value;
    }

}
