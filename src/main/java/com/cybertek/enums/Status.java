package com.cybertek.enums;

public enum Status {

    OPEN("Open"), IN_PROGRESS("In Progress"), UAT_TEST("UAT Test"), COMPLETED("Completed");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
