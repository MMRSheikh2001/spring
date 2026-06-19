package com.wordbridge.project.serviceimpl;

import com.wordbridge.project.dto.mapper.UserProfileMapper;
import com.wordbridge.project.dto.requestdto.UserProfileRequestDTO;
import com.wordbridge.project.dto.responsedto.UserProfileResponseDTO;

import com.wordbridge.project.entity.UserProfile;
import com.wordbridge.project.repository.UserProfileRepository;
import com.wordbridge.project.repository.UserRepository;
import com.wordbridge.project.service.UserProfileService;
import com.wordbridge.project.util.ImageStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;


    private final UserProfileMapper userProfileMapper;


    private final ImageStorageService imageStorageService;

    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserProfileResponseDTO save(UserProfileRequestDTO up, MultipartFile image) {
        if (userProfileRepository.existsByUserId(up.getUserId())) {
            throw new RuntimeException("Profile already exists");
        }
        UserProfile userProfile = userProfileMapper.toEntity(up);

        String email = userRepository.findById(up.getUserId()).orElseThrow(() -> new RuntimeException("No User Found")).getEmail();
        if (image != null && !image.isEmpty()) {
            String fileName = imageStorageService.uploadImage(image, email, "userprofiles");
            userProfile.setImage(fileName);

        }
        UserProfile saved = userProfileRepository.save(userProfile);

        return userProfileMapper.toDTO(saved);
    }

    @Override
    public List<UserProfileResponseDTO> getAll() {
        return userProfileRepository.findAll().stream().map(userProfileMapper::toDTO).toList();
    }

    @Override
    public UserProfileResponseDTO findById(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("User Profile Not Found By this Id"));
        return userProfileMapper.toDTO(userProfile);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No User Profile found"));

        if (userProfile.getImage() != null) {
            imageStorageService.deleteImage(
                    "userprofiles",
                    userProfile.getImage()
            );
        }

        userProfileRepository.delete(userProfile);
    }

    @Override
    @Transactional
    public void deleteImage(Long profileId) {
        UserProfile profile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile Not Found"));

        if (profile.getImage() != null) {

            imageStorageService.deleteImage("userprofiles",
                    profile.getImage()

            );

            profile.setImage(null);

            userProfileRepository.save(profile);
        }
    }

    @Override
    @Transactional
    public UserProfileResponseDTO update(
            Long id,
            UserProfileRequestDTO up,
            MultipartFile image) {

        UserProfile existingProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Profile Not Found"));

        UserProfile userProfile = userProfileMapper.toEntity(up);

        userProfile.setId(id);

        //Preserving  created At
        userProfile.setCreatedAt(existingProfile.getCreatedAt());

        // Keep old image if no new image uploaded
        if (image != null && !image.isEmpty()) {

            String email = userRepository.findById(up.getUserId())
                    .orElseThrow(() -> new RuntimeException("No User Found"))
                    .getEmail();

            String fileName = imageStorageService.uploadImage(
                    image,
                    email,
                    "userprofiles"
            );

            userProfile.setImage(fileName);

        } else {

            userProfile.setImage(existingProfile.getImage());

        }

        UserProfile updated = userProfileRepository.save(userProfile);

        return userProfileMapper.toDTO(updated);
    }


    //Find User Profile By User Id
    @Override
    public UserProfileResponseDTO findByUserId(Long userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId);
        return userProfileMapper.toDTO(userProfile);
    }

    @Override
    public List<UserProfileResponseDTO> findByPresentAddressPoliceStationId(Long id) {
        return userProfileRepository.findByPresentAddressPoliceStationId(id).stream().map(userProfileMapper::toDTO).toList();
    }

    @Override
    public List<UserProfileResponseDTO> findByPresentAddressPoliceStationDistrictId(Long id) {
        return userProfileRepository.findByPresentAddressPoliceStationDistrictId(id).stream().map(userProfileMapper::toDTO).toList();
    }

    @Override
    public List<UserProfileResponseDTO> findByPermanentAddressPoliceStationId(Long id) {
        return userProfileRepository.findByPermanentAddressPoliceStationId(id).stream().map(userProfileMapper::toDTO).toList();
    }

    @Override
    public List<UserProfileResponseDTO> findByPermanentAddressPoliceStationDistrictId(Long id) {
        return userProfileRepository.findByPermanentAddressPoliceStationDistrictId(id).stream().map(userProfileMapper::toDTO).toList();

    }
}
