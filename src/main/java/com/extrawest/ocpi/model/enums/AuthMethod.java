package com.extrawest.ocpi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static com.extrawest.ocpi.util.EnumUtil.findByField;

/**
 * Method used for authentication. Multiple AuthMethods are possible during
 * a charging sessions, for example when the session was started with a reservation: ReserveNow: COMMAND.
 * When the driver arrives and starts charging using a Token that is whitelisted: WHITELIST.
 * The last method SHALL be used in the CDR.
 */
public enum AuthMethod {
    /**
     * Authentication request has been sent to the eMSP.
     */
    AUTH_REQUEST("AUTH_REQUEST"),
    /**
     * Command like StartSession or ReserveNow used to start the Session, the Token provided in the Command was
     * used as authorization.
     */
    COMMAND("COMMAND"),
    /**
     * Whitelist used for authentication, no request to the eMSP has been performed.
     */
    WHITELIST("WHITELIST");
    private final String value;

    AuthMethod(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AuthMethod fromValue(String value) {
        return findByField(
                AuthMethod.class,
                AuthMethod::value,
                value
        );
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }
}
