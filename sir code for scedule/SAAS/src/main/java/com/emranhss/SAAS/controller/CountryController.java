package com.emranhss.SAAS.controller;


import com.emranhss.SAAS.entity.Country;
import com.emranhss.SAAS.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController  {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Optional: GET by code
    @GetMapping("/{code}")
    public Country getCountryByCode(@PathVariable String code) {
        return countryRepository.findAll()
                .stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    // Optional: POST new country
    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }


}
