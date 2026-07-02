package com.emranhss.SAAS.dto.userprofile;

import com.emranhss.SAAS.entity.enums.BloodGroup;
import com.emranhss.SAAS.entity.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class UserProfileRequest {



    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    @NotBlank
    @Pattern(regexp = "^(\\+?880)?1[3-9]\\d{8}$")
    private String phone;

    private String picture;

    private BloodGroup bloodGroup;

    private LocalDate lastBloodDonationDate;

    private Boolean willingToDonateBlood;

    private String spouseName;

    @Pattern(regexp = "^(\\+?880)?1[3-9]\\d{8}$")
    private String spousePhone;

    private Boolean emailNotifications = true;
    private Boolean bloodDonationReminders = true;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getLastBloodDonationDate() {
        return lastBloodDonationDate;
    }

    public void setLastBloodDonationDate(LocalDate lastBloodDonationDate) {
        this.lastBloodDonationDate = lastBloodDonationDate;
    }

    public Boolean getWillingToDonateBlood() {
        return willingToDonateBlood;
    }

    public void setWillingToDonateBlood(Boolean willingToDonateBlood) {
        this.willingToDonateBlood = willingToDonateBlood;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpousePhone() {
        return spousePhone;
    }

    public void setSpousePhone(String spousePhone) {
        this.spousePhone = spousePhone;
    }

    public Boolean getEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(Boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public Boolean getBloodDonationReminders() {
        return bloodDonationReminders;
    }

    public void setBloodDonationReminders(Boolean bloodDonationReminders) {
        this.bloodDonationReminders = bloodDonationReminders;
    }


}
