package com.emranhss.SAAS.service;


import com.emranhss.SAAS.entity.Country;
import com.emranhss.SAAS.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {


    private final CountryRepository countryRepo;

    public CountryService(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }
}
