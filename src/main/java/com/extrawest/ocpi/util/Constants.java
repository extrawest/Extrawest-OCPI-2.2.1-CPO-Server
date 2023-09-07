package com.extrawest.ocpi.util;

public final class Constants {

 /*   Case Insensitive String. Only printable ASCII allowed. (Non-printable characters like: Carriage returns,
    Tabs, Line breaks, etc are not allowed)*/
    public static final String ASCII_REGEXP = "(?i)\\p{Print}+";

    private Constants() {
    }
}
