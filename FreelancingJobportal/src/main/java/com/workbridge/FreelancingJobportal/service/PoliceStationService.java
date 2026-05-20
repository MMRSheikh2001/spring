package com.workbridge.FreelancingJobportal.service;

import com.workbridge.FreelancingJobportal.entity.PoliceStation;
import com.workbridge.FreelancingJobportal.repository.PoliceStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {
    @Autowired
    private PoliceStationRepository policeStationRepository;

    public List<PoliceStation> getAll() {
        return policeStationRepository.findAll();
    }

    public PoliceStation saveOrUpdate(PoliceStation p) {
     return    policeStationRepository.save(p);
    }

    public Optional<PoliceStation> getById(Long id) {
        return policeStationRepository.findById(id);
    }

    public void delete(Long id) {
        policeStationRepository.deleteById(id);
    }


}
