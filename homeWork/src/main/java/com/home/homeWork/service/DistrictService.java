package com.home.homeWork.service;

import com.home.homeWork.dto.DivisionDTO;
import com.home.homeWork.dto.response.DistrictResponseDTO;
import com.home.homeWork.entity.District;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface DistrictService {

    District save(District d);

    List<District> findAll();

    Optional<District> getById(Long id);

    void delete(Integer id);

    List<DistrictResponseDTO> getDistrictByDivisionId(Integer divisionId);
    List<DistrictResponseDTO> getDistrictByDivisionName(String divisionName);

}
