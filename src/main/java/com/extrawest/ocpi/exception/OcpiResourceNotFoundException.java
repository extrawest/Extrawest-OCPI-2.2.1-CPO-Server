package com.extrawest.ocpi.exception;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;


public class OcpiResourceNotFoundException extends RuntimeException {

    public OcpiResourceNotFoundException(String... reasons) {
        super(reasons.length == 0 ? Strings.EMPTY : Arrays.toString(reasons));
    }
}