package com.emranhss.SAAS.repository;

import com.emranhss.SAAS.entity.UserProfile;
import com.emranhss.SAAS.entity.enums.BloodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {


    Optional<UserProfile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);


//    Blood Donation Related Queries (Core Feature)
    List<UserProfile> findByWillingToDonateBloodTrue();

    List<UserProfile> findByBloodGroupAndWillingToDonateBloodTrue(BloodGroup bloodGroup);

    List<UserProfile> findByBloodGroupInAndWillingToDonateBloodTrue(
            List<BloodGroup> bloodGroups
    );

    Page<UserProfile> findByWillingToDonateBloodTrue(Pageable pageable);

    Page<UserProfile> findByBloodGroupAndWillingToDonateBloodTrue(
            BloodGroup bloodGroup,
            Pageable pageable
    );




//    Blood Donation Timing Logic (Very Important)


    List<UserProfile> findByNextBloodDonationDateBeforeAndWillingToDonateBloodTrue(
            LocalDate date
    );

    List<UserProfile> findByLastBloodDonationDateBefore(
            LocalDate date
    );

//    Notification Preferences

    List<UserProfile> findByEmailNotificationsTrue();

    List<UserProfile> findByBloodDonationRemindersTrue();


//    Search / Filter Queries (Admin + UI)

    List<UserProfile> findByFullNameContainingIgnoreCase(String name);

    List<UserProfile> findByGenderIgnoreCase(String gender);

    List<UserProfile> findByBloodGroup(BloodGroup bloodGroup);

//    Dashboard / Analytics Queries

    long countByWillingToDonateBloodTrue();

    long countByBloodGroup(BloodGroup bloodGroup);


//    Custom JPQL (Advanced & Clean)

    @Query("""
        SELECT u FROM UserProfile u
        WHERE u.willingToDonateBlood = true
        AND (u.nextBloodDonationDate IS NULL 
             OR u.nextBloodDonationDate <= :today)
    """)
    List<UserProfile> findEligibleDonors(@Param("today") LocalDate today);

//    Recently registered users

    @Query("""
        SELECT u FROM UserProfile u
        WHERE u.createdAt >= :fromDate
    """)
    List<UserProfile> findRecentlyCreated(@Param("fromDate") LocalDate fromDate);




}