package com.emranhss.SAAS.controller;


import com.emranhss.SAAS.dto.userprofile.UserProfileRequest;
import com.emranhss.SAAS.dto.userprofile.UserProfileResponse;
import com.emranhss.SAAS.dto.userprofile.UserProfileSummaryResponse;
import com.emranhss.SAAS.entity.User;
import com.emranhss.SAAS.entity.enums.BloodGroup;
import com.emranhss.SAAS.service.interfac.UserProfileService;
import jakarta.persistence.TableGenerator;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/api/profiles")

@TableGenerator(name = "User Profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;


//    @GetMapping("/me")
//    public ResponseEntity<UserProfileResponse> getMyProfile(
//            @AuthenticationPrincipal User principal
//    ) {
//        return ResponseEntity.ok(
//                userProfileService.getMyProfile(principal.getId())
//        );
//    }



//    @GetMapping("/me")
//    public ResponseEntity<UserProfileResponse> getMyProfile(
//            @AuthenticationPrincipal User principal
//    ) {
//        System.out.println(principal+" *************************** ");
//        if (principal == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        return ResponseEntity.ok(
//                userProfileService.getMyProfile(principal.getId())
//        );
//    }


    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getMyProfile(
            @AuthenticationPrincipal User principal
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserProfileResponse profile = userProfileService.getMyProfile(principal.getId());

        // if profile is null, return an empty object instead of 403/404
        if (profile == null) {
            profile = new UserProfileResponse(); // empty response
        }

        return ResponseEntity.ok(profile);
    }




//Post
//    {
//        "firstName": "Emran",
//            "lastName": "Hossain",
//            "dateOfBirth": "2000-01-01",
//            "gender": "MALE",
//            "picture": "https://example.com/pic.jpg",
//            "bloodGroup": "A_POS",
//            "lastBloodDonationDate": "2025-12-01",
//            "nextBloodDonationDate": "2026-06-01",
//            "spouseName": "Sadia",
//            "spousePhone": "+8801619192323",
//            "phone":"+8801619192323",
//            "emailNotifications": true,
//            "bloodDonationReminders": true,
//            "willingToDonateBlood": true
//
//
//    }

//    response
//
//    {
//        "active": true,
//            "bloodDonationReminders": true,
//            "bloodGroup": "A_POS",
//            "createdAt": "2026-01-21T00:34:19.264897",
//            "dateOfBirth": "2000-01-01",
//            "emailNotifications": true,
//            "firstName": "Emran",
//            "fullName": "Emran Hossain",
//            "gender": "MALE",
//            "id": 2,
//            "lastBloodDonationDate": "2025-12-01",
//            "lastName": "Hossain",
//            "nextBloodDonationDate": null,
//            "phone": "01619192323",
//            "picture": null,
//            "spouseName": "Sadia",
//            "spousePhone": "+8801619192323",
//            "updatedAt": "2026-01-21T00:34:19.264897",
//            "willingToDonateBlood": true
//    }


    /**
     * ✏️ Create or update logged-in user's profile
     */
//    @PostMapping("/me")
//    public ResponseEntity<UserProfileResponse> createOrUpdateProfile(
//            @AuthenticationPrincipal User principal,
//            @RequestBody @Valid UserProfileRequest request
//    ) {
//
//        System.out.println(principal+" *************************** ");
//        return ResponseEntity.ok(
//                userProfileService.createOrUpdateProfile(principal.getId(), request)
//        );
//    }


    @PostMapping("/me")
    public ResponseEntity<UserProfileResponse> createOrUpdateProfile(
            @AuthenticationPrincipal User principal,
            @RequestPart("profile") @Valid UserProfileRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return ResponseEntity.ok(
                userProfileService.createOrUpdateProfile(principal.getId(), request, file)
        );
    }




    /**
     * 👁 Lightweight summary (dashboard / card)
     */
    @GetMapping("/me/summary")
    public ResponseEntity<UserProfileSummaryResponse> getMySummary(
            @AuthenticationPrincipal User principal
    ) {
        return ResponseEntity.ok(
                userProfileService.getProfileSummary(principal.getId())
        );
    }

    /**
     * 🩸 Public donor list (with pagination & filter)
     */
    @GetMapping("/donors")
    public ResponseEntity<Page<UserProfileSummaryResponse>> getDonors(
            @RequestParam(required = false) BloodGroup bloodGroup,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fullName") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sortOrder = direction.equalsIgnoreCase("desc")
                ? Sort.by(sort).descending()
                : Sort.by(sort).ascending();

        Pageable pageable = PageRequest.of(page, size, sortOrder);

        return ResponseEntity.ok(
                userProfileService.getDonors(bloodGroup, pageable)
        );
    }


}
