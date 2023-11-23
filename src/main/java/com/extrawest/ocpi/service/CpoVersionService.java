package com.extrawest.ocpi.service;


import com.extrawest.ocpi.model.dto.VersionDetailsDto;
import com.extrawest.ocpi.model.dto.VersionDto;
import com.extrawest.ocpi.model.enums.VersionNumber;

import java.util.List;

public interface CpoVersionService {

    List<VersionDto> getVersion();

    VersionDetailsDto getVersionDetails(VersionNumber version);

}
