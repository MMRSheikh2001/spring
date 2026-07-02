package com.emranhss.SAAS.service;

import com.emranhss.SAAS.entity.City;
import com.emranhss.SAAS.repository.CityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepo;

    public CityService(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }

    public List<City> getCitiesByStateId(Long stateId) {
        return cityRepo.findByStateId(stateId);
    }

    public City getCityById(Long id) {
        return cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));
    }
}
