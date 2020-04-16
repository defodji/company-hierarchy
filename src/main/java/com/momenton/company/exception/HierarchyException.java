package com.momenton.company.exception;

public class HierarchyException extends Exception {
    public static final String INVALID_MANAGER = "Employee with Invalid Manager";
    public static final String INVALID_NUMBER_CEOS = "No CEO or many CEOs found";

    public HierarchyException(String errorMessage) {
        super(errorMessage);
    }
}
