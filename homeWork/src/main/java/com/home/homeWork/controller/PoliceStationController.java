package com.home.homeWork.controller;

import com.home.homeWork.entity.PoliceStation;
import com.home.homeWork.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policestations/")
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
