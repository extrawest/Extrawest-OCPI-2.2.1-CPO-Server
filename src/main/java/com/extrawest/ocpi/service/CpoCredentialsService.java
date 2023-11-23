package com.extrawest.ocpi.service;

import com.extrawest.ocpi.model.dto.CredentialsDto;

public interface CpoCredentialsService {

    CredentialsDto getCredentials();

    CredentialsDto postCredentials(CredentialsDto credentialsDTO);

    void putCredentials(CredentialsDto credentialsDTO);

    void deleteCredentials(CredentialsDto credentialsDTO);

}
