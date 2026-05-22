package com.home.homeWork.serviceimpl;

import com.home.homeWork.entity.Country;
import com.home.homeWork.entity.Division;
import com.home.homeWork.repository.CountryRepository;
import com.home.homeWork.repository.DivisionRepository;
import com.home.homeWork.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Division save(Division c) {

        Integer countryId = c.getCountry().getId();
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException(("Country Not found with this Id")));
        c.setCountry(country);
        return divisionRepository.save(c);

    }

    @Override
    public List<Division> findAll() {
        return divisionRepository.findAll();
    }

    @Override
    public Optional<Division> getById(Integer id) {
        return divisionRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        divisionRepository.deleteById(id);
    }

    @Override
    public List<Division> getDivisionByCountryId(Integer countryId) {
        return divisionRepository.findByCountryId(countryId);
    }

    @Override
    public List<Division> getDivisionByCountryName(String countryName) {
        return divisionRepository.findByCountryName(countryName);
    }
}
