package com.home.homeWork.serviceimpl;

import com.home.homeWork.dto.DivisionDTO;
import com.home.homeWork.entity.Country;
import com.home.homeWork.entity.Division;
import com.home.homeWork.repository.CountryRepository;
import com.home.homeWork.repository.DivisionRepository;
import com.home.homeWork.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<DivisionDTO> getDivisionByCountryId(Integer countryId) {
        List<Division> list = divisionRepository.findByCountryId(countryId);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DivisionDTO> getDivisionByCountryName(String countryName) {
        List<Division> list = divisionRepository.findByCountryName(countryName);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    private DivisionDTO convertToDTO(Division division) {
        return new DivisionDTO(
                division.getId(),
                division.getName(),
                division.getCountry().getName(),
                division.getCountry().getId()
        );
    }
}
