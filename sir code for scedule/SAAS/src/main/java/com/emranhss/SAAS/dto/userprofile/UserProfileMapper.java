package com.emranhss.SAAS.dto.userprofile;

import com.emranhss.SAAS.entity.UserProfile;

public class UserProfileMapper {

//    public static void updateEntity(UserProfile profile, UserProfileRequest r) {
//
//        profile.setFirstName(r.getFirstName());
//        profile.setLastName(r.getLastName());
//
//        profile.setDateOfBirth(r.getDateOfBirth());
//        profile.setGender(r.getGender());
//        profile.setBloodGroup(r.getBloodGroup());
//
//        profile.setLastBloodDonationDate(r.getLastBloodDonationDate());
////        profile.setNextBloodDonationDate(r.getNextBloodDonationDate());
//
//        profile.setSpouseName(r.getSpouseName());
//        profile.setSpousePhone(r.getSpousePhone());
//
//        profile.setEmailNotifications(r.getEmailNotifications());
//        profile.setBloodDonationReminders(r.getBloodDonationReminders());
//        profile.setWillingToDonateBlood(r.getWillingToDonateBlood());
//    }


public static void updateEntity(UserProfile entity, UserProfileRequest request) {
    entity.setFirstName(request.getFirstName());
    entity.setLastName(request.getLastName());
    entity.setDateOfBirth(request.getDateOfBirth());
    entity.setGender(request.getGender());
    entity.setPhone(request.getPhone());
    // Only update picture if request has it (usually from JSON)
    if (request.getPicture() != null) {
        entity.setPicture(request.getPicture());
    }
    entity.setBloodGroup(request.getBloodGroup());
    entity.setLastBloodDonationDate(request.getLastBloodDonationDate());

    entity.setWillingToDonateBlood(request.getWillingToDonateBlood());
    entity.setSpouseName(request.getSpouseName());
    entity.setSpousePhone(request.getSpousePhone());
    entity.setEmailNotifications(request.getEmailNotifications());
    entity.setBloodDonationReminders(request.getBloodDonationReminders());
    entity.setActive(true); // default for new profile

}


    public static UserProfileResponse toResponse(UserProfile profile) {
        UserProfileResponse response = new UserProfileResponse();

        response.setId(profile.getId());
        response.setFirstName(profile.getFirstName());
        response.setLastName(profile.getLastName());
        response.setFullName(profile.getFullName());

        response.setDateOfBirth(profile.getDateOfBirth());
        response.setGender(profile.getGender());

        // Phone comes from linked User entity
        response.setPhone(profile.getUser() != null ? profile.getUser().getPhone() : null);
        response.setPicture(profile.getPicture());

        response.setBloodGroup(profile.getBloodGroup());
        response.setLastBloodDonationDate(profile.getLastBloodDonationDate());
        response.setNextBloodDonationDate(profile.getNextBloodDonationDate());

        response.setWillingToDonateBlood(profile.isWillingToDonateBlood());

        response.setSpouseName(profile.getSpouseName());
        response.setSpousePhone(profile.getSpousePhone());

        response.setEmailNotifications(profile.isEmailNotifications());
        response.setBloodDonationReminders(profile.isBloodDonationReminders());

        response.setActive(profile.isActive());

        response.setCreatedAt(profile.getCreatedAt());
        response.setUpdatedAt(profile.getUpdatedAt());
        response.setPicture(profile.getPicture());

        return response;
    }

    public static UserProfileSummaryResponse toSummary(UserProfile p) {
        UserProfileSummaryResponse r = new UserProfileSummaryResponse();
        r.setId(p.getId());
        r.setFullName(p.getFullName());
        r.setPhone(p.getUser().getPhone());
        r.setBloodGroup(p.getBloodGroup());
        r.setWillingToDonateBlood(p.isWillingToDonateBlood());

        return r;
    }



}