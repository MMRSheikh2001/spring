package com.emranhss.SAAS.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AddressRequest {

    private Long id;
    private Long userId;

    private String countryCode;

    private Long admin1Id;
    private String admin1Name; // Division / State

    private Long admin2Id;
    private String admin2Name; // District / County

    private Long admin3Id;
    private String admin3Name; // City / Upazila

    private String addressLine1;
    private String addressLine2;
    private String postalCode;

    private LocalDateTime createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Long getAdmin1Id() {
        return admin1Id;
    }

    public void setAdmin1Id(Long admin1Id) {
        this.admin1Id = admin1Id;
    }

    public String getAdmin1Name() {
        return admin1Name;
    }

    public void setAdmin1Name(String admin1Name) {
        this.admin1Name = admin1Name;
    }

    public Long getAdmin2Id() {
        return admin2Id;
    }

    public void setAdmin2Id(Long admin2Id) {
        this.admin2Id = admin2Id;
    }

    public String getAdmin2Name() {
        return admin2Name;
    }

    public void setAdmin2Name(String admin2Name) {
        this.admin2Name = admin2Name;
    }

    public Long getAdmin3Id() {
        return admin3Id;
    }

    public void setAdmin3Id(Long admin3Id) {
        this.admin3Id = admin3Id;
    }

    public String getAdmin3Name() {
        return admin3Name;
    }

    public void setAdmin3Name(String admin3Name) {
        this.admin3Name = admin3Name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
