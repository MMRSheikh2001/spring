package com.workbridge.FreelancingJobportal.repository;

import com.workbridge.FreelancingJobportal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
