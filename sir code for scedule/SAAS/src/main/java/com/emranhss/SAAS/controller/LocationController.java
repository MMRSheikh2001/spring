package com.emranhss.SAAS.controller;


import com.emranhss.SAAS.entity.*;
import com.emranhss.SAAS.entity.enums.LocationLevel;
import com.emranhss.SAAS.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationRepository locationRepo;
    private final LocationLabelConfigRepository labelRepo;

    public LocationController(LocationRepository locationRepo,
                              LocationLabelConfigRepository labelRepo) {
        this.locationRepo = locationRepo;
        this.labelRepo = labelRepo;
    }

    @GetMapping("/admin1/{countryCode}")
    public List<Location> getAdminLevel1(@PathVariable String countryCode) {
        return locationRepo.findByCountry_CodeAndLevel(
                countryCode, LocationLevel.ADMIN_LEVEL_1);
    }

    @GetMapping("/children/{parentId}")
    public List<Location> getChildren(@PathVariable Long parentId) {
        return locationRepo.findByParentId(parentId);
    }

    @GetMapping("/labels/{countryCode}")
    public Map<LocationLevel, String> getLabels(@PathVariable String countryCode) {
        return labelRepo.findByCountryCode(countryCode)
                .stream()
                .collect(Collectors.toMap(
                        LocationLabelConfig::getLevel,
                        LocationLabelConfig::getLabel
                ));
    }
}
