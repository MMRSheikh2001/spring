package com.home.homeWork.service;



import com.home.homeWork.dto.DivisionDTO;
import com.home.homeWork.entity.Division;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DivisionService {
    Division save(Division c);

    List<Division> findAll();

    Optional<Division> getById(Integer id);

    void delete(Integer id);

    List<DivisionDTO> getDivisionByCountryId(Integer countryId);
    List<DivisionDTO> getDivisionByCountryName(String countryName);
}
