package com.emranhss.SAAS.repository;

import com.emranhss.SAAS.entity.Location;
import com.emranhss.SAAS.entity.enums.LocationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByCountryCodeAndLevel(String countryCode, LocationLevel level);
    List<Location> findByCountry_CodeAndLevel(String countryCode, LocationLevel level);

    // Get children locations
    List<Location> findByParentId(Long parentId);

    // Optional: get name directly
    default String findNameById(Long id) {
        return findById(id).map(Location::getName).orElse(null);
    }

}

