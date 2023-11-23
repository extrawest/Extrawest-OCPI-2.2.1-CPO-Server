package com.extrawest.ocpi.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    //https://github.com/ocpi/ocpi/blob/480973547169dee2fe6d12b1a0fe604623efcbb5/terminology.asciidoc#typical-ocpi-implementations-per-role
    CDRS_SENDER("cdrs:send"),
    CDRS_RECEIVER("cdrs:receive"),
    CHARGING_PROFILES_SENDER("chargingprofiles:send"),
    CHARGING_PROFILES_RECEIVER("chargingprofiles:receive"),
    COMMANDS_SENDER("commands:send"),
    COMMANDS_RECEIVER("commands:receive"),
    CREDENTIALS_SENDER("credentials:send"),
    CREDENTIALS_RECEIVER("credentials:receive"),
    HUB_CLIENT_INFO_SENDER("hubclientinfo:send"),
    HUB_CLIENT_INFO_RECEIVER("hubclientinfo:receive"),
    LOCATIONS_SENDER("locations:send"),
    LOCATIONS_RECEIVER("locations:receive"),
    SESSIONS_SENDER("sessions:send"),
    SESSIONS_RECEIVER("sessions:receive"),
    TARIFFS_SENDER("tariffs:send"),
    TARIFFS_RECEIVER("tariffs:receive"),
    TOKENS_SENDER("tokens:send"),
    TOKENS_RECEIVER("tokens:receive"),
    VERSIONS_RECEIVER("versions:send"),
    VERSIONS_SENDER("versions:receive");

    private final String permission;
}
