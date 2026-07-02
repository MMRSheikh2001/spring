package com.emranhss.SAAS.service;

import com.emranhss.SAAS.dto.userprofile.UserProfileMapper;
import com.emranhss.SAAS.dto.userprofile.UserProfileRequest;
import com.emranhss.SAAS.dto.userprofile.UserProfileResponse;
import com.emranhss.SAAS.dto.userprofile.UserProfileSummaryResponse;
import com.emranhss.SAAS.entity.User;
import com.emranhss.SAAS.entity.UserProfile;
import com.emranhss.SAAS.entity.enums.BloodGroup;
import com.emranhss.SAAS.repository.UserProfileRepository;
import com.emranhss.SAAS.repository.UserRepository;
import com.emranhss.SAAS.service.file.FileStorageService;
import com.emranhss.SAAS.service.interfac.UserProfileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private FileStorageService fileStorageService;


    @Override
    public UserProfileResponse getMyProfile(Long userId) {
        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return UserProfileMapper.toResponse(profile);
    }

//    @Override
//    public UserProfileResponse createOrUpdateProfile(Long userId, UserProfileRequest request) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        UserProfile profile = profileRepository
//                .findByUserId(userId)
//                .orElse(new UserProfile());
//
//        if (profile.getLastBloodDonationDate() != null) {
//            profile.setNextBloodDonationDate(profile.getLastBloodDonationDate().plusDays(90));
//        }
//
//        profile.setUser(user);
//        UserProfileMapper.updateEntity(profile, request);
//
//        return UserProfileMapper.toResponse(profileRepository.save(profile));
//    }


    @Override
    public UserProfileResponse createOrUpdateProfile(Long userId, UserProfileRequest request, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile profile = profileRepository
                .findByUserId(userId)
                .orElseGet(() -> {
                    UserProfile p = new UserProfile();
                    p.setUser(user); // 🔑 must set user
                    return p;
                });
        String fileName = "";

        // Handle picture upload
        if (file != null && !file.isEmpty()) {
            try {
                 fileName = fileStorageService.save(file);

                System.out.println("File name is " + fileName);
                profile.setPicture(fileName);
                request.setPicture(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file", e);
            }
        }

        if (profile.getLastBloodDonationDate() != null) {
            profile.setNextBloodDonationDate(profile.getLastBloodDonationDate().plusDays(120));
        }

        UserProfileMapper.updateEntity(profile, request);

        return UserProfileMapper.toResponse(profileRepository.save(profile));
    }


//    public UserProfileResponse createOrUpdateProfile(Long userId, UserProfileRequest request) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        UserProfile profile = profileRepository
//                .findByUserId(userId)
//                .orElseGet(() -> {
//                    UserProfile p = new UserProfile();
//                    p.setUser(user); // 🔑 must set user
//                    return p;
//                });
//
//        if (profile.getLastBloodDonationDate() != null) {
//            profile.setNextBloodDonationDate(profile.getLastBloodDonationDate().plusDays(90));
//        }
//
//        UserProfileMapper.updateEntity(profile, request);
//
//        return UserProfileMapper.toResponse(profileRepository.save(profile));
//    }


    @Override
    public UserProfileSummaryResponse getProfileSummary(Long userId) {
        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return UserProfileMapper.toSummary(profile);
    }

    @Override
    public Page<UserProfileSummaryResponse> getDonors(
            BloodGroup bloodGroup,
            Pageable pageable
    ) {
        Page<UserProfile> page = (bloodGroup == null)
                ? profileRepository.findByWillingToDonateBloodTrue(pageable)
                : profileRepository.findByBloodGroupAndWillingToDonateBloodTrue(bloodGroup, pageable);

        return page.map(UserProfileMapper::toSummary);
    }
}
