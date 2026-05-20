package com.workbridge.FreelancingJobportal.service;

import com.workbridge.FreelancingJobportal.entity.Department;
import com.workbridge.FreelancingJobportal.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
   @Autowired
    private DepartmentRepository departmentRepository;

   public List<Department> getAll(){
       return departmentRepository.findAll();
   }
   public Department saveOrUpdate(Department d){
       return departmentRepository.save(d);
   }
   public void  delete(Long id){
       departmentRepository.deleteById(id);
   }
   public Optional<Department> findById(Long id){
       return departmentRepository.findById(id);
   }


}
