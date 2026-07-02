package com.emranhss.SAAS.service.interfac;

import com.emranhss.SAAS.dto.userprofile.UserProfileRequest;
import com.emranhss.SAAS.dto.userprofile.UserProfileResponse;
import com.emranhss.SAAS.dto.userprofile.UserProfileSummaryResponse;
import com.emranhss.SAAS.entity.enums.BloodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


public interface UserProfileService {

    UserProfileResponse getMyProfile(Long userId);

    UserProfileResponse createOrUpdateProfile(Long userId, UserProfileRequest request, MultipartFile file);

    UserProfileSummaryResponse getProfileSummary(Long userId);

    Page<UserProfileSummaryResponse> getDonors(
            BloodGroup bloodGroup,
            Pageable pageable
    );


}
