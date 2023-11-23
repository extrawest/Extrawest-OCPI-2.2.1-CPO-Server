package com.extrawest.ocpi.controller;

import com.extrawest.ocpi.model.dto.ResponseFormat;
import com.extrawest.ocpi.model.dto.VersionDetailsDto;
import com.extrawest.ocpi.model.dto.VersionDto;
import com.extrawest.ocpi.model.enums.VersionNumber;
import com.extrawest.ocpi.model.enums.status_codes.OcpiStatusCode;
import com.extrawest.ocpi.service.CpoVersionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cpo/api/versions")
@Tag(name = "CpoVersion")
public class CpoVersionController {

    protected final CpoVersionService cpoVersionService;

    protected CpoVersionController(@Autowired CpoVersionService cpoVersionService) {
        this.cpoVersionService = cpoVersionService;
    }

    /**
     * Fetch information about the supported versions.
     *
     * @return list of VersionResponseDTO
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<VersionDto>>> getVersions() {
        List<VersionDto> versions = cpoVersionService.getVersions();

        ResponseFormat<List<VersionDto>> responseFormat = new ResponseFormat<List<VersionDto>>()
                .build(OcpiStatusCode.SUCCESS, versions);
        return ResponseEntity.ok(responseFormat);
    }

    /**
     * Via the version details, the parties can exchange which modules are implemented for a specific version of OCPI,
     * which interface role is implemented, and what the endpoint URL is for this interface.
     *
     * @param version - version of OCPI
     * @return VersionDetails
     */
    @GetMapping("/details")
    public ResponseEntity<ResponseFormat<VersionDetailsDto>> getVersionDetails(
            @RequestParam(value = "version") String version) {
        VersionDetailsDto versionDetails = cpoVersionService.getVersionDetails(VersionNumber.fromValue(version));

        ResponseFormat<VersionDetailsDto> responseFormat = new ResponseFormat<VersionDetailsDto>()
                .build(OcpiStatusCode.SUCCESS, versionDetails);
        return ResponseEntity.ok(responseFormat);
    }
}
