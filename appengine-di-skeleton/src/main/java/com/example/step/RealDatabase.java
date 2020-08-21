package com.example.step;

public class RealDatabase implements Database {
    private String value;

    public RealDatabase(String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }
}