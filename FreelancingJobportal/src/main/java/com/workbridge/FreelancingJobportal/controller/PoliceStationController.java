package com.workbridge.FreelancingJobportal.controller;

import com.workbridge.FreelancingJobportal.entity.PoliceStation;
import com.workbridge.FreelancingJobportal.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/policestation/")
public class PoliceStationController {
    @Autowired
    private PoliceStationService policeStationService;

    @PostMapping
    public ResponseEntity<PoliceStation> save(@RequestBody PoliceStation p) {
        PoliceStation savedPoliceStation = policeStationService.saveOrUpdate(p);
        return new ResponseEntity<>(savedPoliceStation, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PoliceStation> getAll() {
        return policeStationService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<PoliceStation> getById(@PathVariable Long id) {
        PoliceStation policeStation = policeStationService.getById(id)
                .orElseThrow(() -> new RuntimeException("Police Station not Found"));

        return ResponseEntity.ok(policeStation);
    }
}
