package com.home.homeWork.serviceimpl;

import com.home.homeWork.entity.Country;
import com.home.homeWork.repository.CountryRepository;
import com.home.homeWork.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save(Country c) {
        return countryRepository.save(c);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getById(Integer id) {
        return countryRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }
}
