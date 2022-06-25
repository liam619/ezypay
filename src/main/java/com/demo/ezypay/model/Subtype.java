package com.demo.ezypay.model;

public enum Subtype {

    DAILY(1), WEEKLY(2), MONTHLY(3);

    private final int type;

    Subtype(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
