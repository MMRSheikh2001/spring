package com.emranhss.SAAS.dto.userprofile;


import com.emranhss.SAAS.entity.enums.BloodGroup;
import com.emranhss.SAAS.entity.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserProfileResponse {

    private Long id;

    private String firstName;
    private String lastName;
    private String fullName;

    private LocalDate dateOfBirth;
    private Gender gender;

    private String phone;
    private String picture;

    private BloodGroup bloodGroup;
    private LocalDate lastBloodDonationDate;
    private LocalDate nextBloodDonationDate;

    private boolean willingToDonateBlood;

    private String spouseName;
    private String spousePhone;

    private boolean emailNotifications;
    private boolean bloodDonationReminders;

    private boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters & setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public LocalDate getNextBloodDonationDate() {
        return nextBloodDonationDate;
    }

    public void setNextBloodDonationDate(LocalDate nextBloodDonationDate) {
        this.nextBloodDonationDate = nextBloodDonationDate;
    }

    public boolean isWillingToDonateBlood() {
        return willingToDonateBlood;
    }

    public void setWillingToDonateBlood(boolean willingToDonateBlood) {
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

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public boolean isBloodDonationReminders() {
        return bloodDonationReminders;
    }

    public void setBloodDonationReminders(boolean bloodDonationReminders) {
        this.bloodDonationReminders = bloodDonationReminders;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
