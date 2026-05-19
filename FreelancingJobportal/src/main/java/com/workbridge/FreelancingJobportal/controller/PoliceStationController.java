package com.workbridge.FreelancingJobportal.controller;

import com.workbridge.FreelancingJobportal.entity.PoliceStation;
import com.workbridge.FreelancingJobportal.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policestation/")
public class PoliceStationController {
    @Autowired
    private PoliceStationService policeStationService;

    @PostMapping
    public void save(@RequestBody PoliceStation p) {
        policeStationService.saveOrUpdate(p);
    }

    @GetMapping
    public List<PoliceStation> getAll() {
        return policeStationService.getAll();
    }
}
