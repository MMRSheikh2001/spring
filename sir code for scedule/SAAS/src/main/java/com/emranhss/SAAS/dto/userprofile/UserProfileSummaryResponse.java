package com.emranhss.SAAS.dto.userprofile;

import com.emranhss.SAAS.entity.enums.BloodGroup;

public class UserProfileSummaryResponse {

    private Long id;
    private String fullName;
    private String phone;
    private BloodGroup bloodGroup;
    private boolean willingToDonateBlood;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public boolean isWillingToDonateBlood() {
        return willingToDonateBlood;
    }

    public void setWillingToDonateBlood(boolean willingToDonateBlood) {
        this.willingToDonateBlood = willingToDonateBlood;
    }
}
