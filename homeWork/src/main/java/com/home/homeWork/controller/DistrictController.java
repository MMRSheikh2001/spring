package com.home.homeWork.controller;

import com.home.homeWork.dto.response.DistrictResponseDTO;
import com.home.homeWork.entity.District;
import com.home.homeWork.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/district/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @PostMapping
    public ResponseEntity<District> save(@RequestBody District d) {
        District savedDistrict = districtService.save(d);
        return ResponseEntity.ok(savedDistrict);
    }

    @GetMapping
    public ResponseEntity<List<District>> getAll() {
        List<District> list = districtService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("division/{id}")
    public ResponseEntity<List<DistrictResponseDTO>> getDistrictByDivisionId(@PathVariable Integer id) {
        List<DistrictResponseDTO> list = districtService.getDistrictByDivisionId(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("division/name/{name}")
    public ResponseEntity<List<DistrictResponseDTO>> getDistrictByDivisionName(@PathVariable String name) {
        List<DistrictResponseDTO> list = districtService.getDistrictByDivisionName(name);
        return ResponseEntity.ok(list);
    }
}
