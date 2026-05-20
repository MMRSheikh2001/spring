package com.workbridge.FreelancingJobportal.controller;

import com.workbridge.FreelancingJobportal.entity.Department;
import com.workbridge.FreelancingJobportal.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department/")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> save(@RequestBody Department d) {
        Department savedDepartment = departmentService.saveOrUpdate(d);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }
}
