package com.home.homeWork.serviceimpl;

import com.home.homeWork.dto.DivisionDTO;
import com.home.homeWork.dto.response.DistrictResponseDTO;
import com.home.homeWork.entity.District;
import com.home.homeWork.entity.Division;
import com.home.homeWork.repository.DistrictRepository;
import com.home.homeWork.repository.DivisionRepository;
import com.home.homeWork.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    DivisionRepository divisionRepository;

    @Override
    public District save(District d) {
        Integer divisionId = d.getDivision().getId();
        Division dv = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division Not found with this Id"));
        d.setDivision(dv);

        return districtRepository.save(d);
    }

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public Optional<District> getById(Long id) {
        return districtRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<DistrictResponseDTO> getDistrictByDivisionId(Integer divisionId) {
        List<District> list = districtRepository.findByDivisionId(divisionId);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DistrictResponseDTO> getDistrictByDivisionName(String divisionName) {
        List<District> list = districtRepository.findByDivisionName(divisionName);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    private DistrictResponseDTO convertToDTO(District district) {
        return new DistrictResponseDTO(
                district.getId(),
                district.getName(),
                Long.valueOf(district.getDivision().getId()),
                district.getDivision().getName(),
                Long.valueOf(district.getDivision().getCountry().getId()),
                district.getDivision().getCountry().getName(),
                district.getDivision().getCountry().getCode()
        );
    }
}
