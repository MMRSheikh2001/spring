package com.emranhss.SAAS.repository;

import com.emranhss.SAAS.entity.LocationLabelConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationLabelConfigRepository
        extends JpaRepository<LocationLabelConfig, Long> {

    List<LocationLabelConfig> findByCountryCode(String countryCode);
}
