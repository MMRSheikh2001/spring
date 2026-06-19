package com.wordbridge.project.repository;

import com.wordbridge.project.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUserId(Long id);

    boolean existsByUserId(Long userId);

    List<UserProfile> findByPresentAddressPoliceStationId(Long id);

    List<UserProfile> findByPresentAddressPoliceStationDistrictId(Long id);


    List<UserProfile> findByPermanentAddressPoliceStationId(Long id);

    List<UserProfile> findByPermanentAddressPoliceStationDistrictId(Long id);

}
