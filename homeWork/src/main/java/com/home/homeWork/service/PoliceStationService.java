package com.home.homeWork.service;

import com.home.homeWork.entity.PoliceStation;
import com.home.homeWork.repository.PoliceStationRepository;
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

    public void saveOrUpdate(PoliceStation p) {
        policeStationRepository.save(p);
    }

    public Optional<PoliceStation> getById(Long id) {
        return policeStationRepository.findById(id);
    }

    public void delete(Long id) {
        policeStationRepository.deleteById(id);
    }

}
