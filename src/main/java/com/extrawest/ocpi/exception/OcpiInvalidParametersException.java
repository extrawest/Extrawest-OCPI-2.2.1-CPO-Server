package com.extrawest.ocpi.exception;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public class OcpiInvalidParametersException extends RuntimeException {

    public OcpiInvalidParametersException(String... reasons) {
        super(reasons.length == 0 ? Strings.EMPTY : Arrays.toString(reasons));
    }
}