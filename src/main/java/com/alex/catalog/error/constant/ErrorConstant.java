package com.alex.catalog.error.constant;

public enum ErrorConstant {
    
    MANUFACTURE_NOT_FOUND("Couldn't find the manufacturer"),
    MACHINE_PART_NOT_FOUND("Couldn't find the machine parts"),
    MACHINE_NOT_FOUND("Could not find machine");

    private final String name;

    ErrorConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
