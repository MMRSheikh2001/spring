package com.workbridge.FreelancingJobportal.repository;

import com.workbridge.FreelancingJobportal.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation,Long> {
}
