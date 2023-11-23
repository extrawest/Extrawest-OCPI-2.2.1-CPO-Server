package com.extrawest.ocpi.model.enums.status_codes;

import lombok.Getter;

@Getter
public enum OcpiStatusCode {
    /**
     * {@code 1000 Success}.
     *
     * @see <a href="https://github.com/ocpi/ocpi/blob/master/status_codes.asciidoc#1xxx-success">: 1xxx: Success</a>
     */
    SUCCESS(1000, "Success"),

    /**
     * {@code 2000 Client errors}.
     *
     * @see <a href="https://github.com/ocpi/ocpi/blob/master/status_codes.asciidoc#2xxx-client-errors">: 2xxx: Client errors</a>
     */
    CLIENT_ERROR(2000, "Generic client error"),
    INVALID_PARAMETERS(2001, "Invalid or missing parameters"),
    NOT_ENOUGH_INFORMATION(2002, "Not enough information"),
    UNKNOWN_LOCATION(2003, "Unknown Location"),
    UNKNOWN_TOKEN(2004, "Unknown Token"),

    /**
     * {@code 3000 Server errors}.
     *
     * @see <a href="https://github.com/ocpi/ocpi/blob/master/status_codes.asciidoc#3xxx-server-errors">: 3xxx: Server errors</a>
     */
    SERVER_ERROR(3000, "Generic server error"),
    UNABLE_TO_USE_API_ERROR(3001, "Unable to use the client’s API"),
    UNSUPPORTED_VERSION_ERROR(3002, "Unsupported version"),
    MISSION_ENDPOINT_ERROR(3003, "No matching endpoints or expected endpoints missing between parties"),

    /**
     * {@code 4000 Hub errors}.
     *
     * @see <a href="https://github.com/ocpi/ocpi/blob/master/status_codes.asciidoc#4xxx-hub-errors">: 4xxx: Hub errors</a>
     */
    HUB_ERROR(4000, "Generic hub error"),
    UNKNOWN_RECEIVER_HUB_ERROR(4001, "Unknown receiver (TO address is unknown)"),
    TIMED_OUT_FORWARDED_REQUEST_HUB_ERROR(4002, "Timeout on forwarded request (message is forwarded, but request times out)"),
    CONNECTION_PROBLEM_HUB_ERROR(4003, "Connection problem (receiving party is not connected)");

    private final int value;
    private final String reasonPhrase;

    OcpiStatusCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
}
