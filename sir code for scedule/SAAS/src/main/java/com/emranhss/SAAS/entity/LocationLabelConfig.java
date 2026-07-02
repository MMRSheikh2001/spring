package com.emranhss.SAAS.entity;

import com.emranhss.SAAS.entity.enums.LocationLevel;
import jakarta.persistence.*;

@Entity
@Table(name = "location_label_config")
public class LocationLabelConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String countryCode;

    @Enumerated(EnumType.STRING)
    private LocationLevel level;

    private String label;  // Division, State, Police Station


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocationLevel getLevel() {
        return level;
    }

    public void setLevel(LocationLevel level) {
        this.level = level;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

