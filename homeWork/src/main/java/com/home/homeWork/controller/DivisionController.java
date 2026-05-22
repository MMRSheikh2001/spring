package com.home.homeWork.controller;

import com.home.homeWork.entity.Division;
import com.home.homeWork.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/divisions/")
public class DivisionController {
    @Autowired
    private DivisionService divisionService;

    @PostMapping
    public ResponseEntity<Division> save(@RequestBody Division d) {
        Division savedDivision = divisionService.save(d);
        return ResponseEntity.ok(savedDivision);
    }

    @GetMapping
    public ResponseEntity<List<Division>> getAll() {
        List<Division> list = divisionService.findAll();
        return ResponseEntity.ok(list);
    }

    //Find By Country Id
    @GetMapping("country/{id}")
    public List<Division> getByCountryId(@PathVariable Integer id) {
        return divisionService.getDivisionByCountryId(id);
    }

    //Find By Country Name
    @GetMapping("country/name/{name}")
    public List<Division> getByCountryName(@PathVariable String name) {
        List<Division> lIst = divisionService.getDivisionByCountryName(name);
        return lIst;
    }


}
