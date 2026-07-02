package com.emranhss.SAAS.service;

import com.emranhss.SAAS.dto.address.AddressRequest;
import com.emranhss.SAAS.dto.address.AddressResponse;
import com.emranhss.SAAS.entity.Address;
import com.emranhss.SAAS.repository.AddressRepository;
import com.emranhss.SAAS.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private  AddressRepository addressRepository;
    @Autowired
    private LocationRepository locationRepository;

    public Address save(AddressRequest req) {

        if (req.getUserId() == null) {
            throw new IllegalArgumentException("User ID is mandatory");
        }

        Address address = new Address();
        address.setUserId(req.getUserId());
        address.setCountryCode(req.getCountryCode());
        address.setAdmin1Id(req.getAdmin1Id());
        address.setAdmin2Id(req.getAdmin2Id());
        address.setAdmin3Id(req.getAdmin3Id());
        address.setAddressLine1(req.getAddressLine1());
        address.setAddressLine2(req.getAddressLine2());
        address.setPostalCode(req.getPostalCode());

        return addressRepository.save(address);
    }

    public List<Address> getByUser(Long userId) {
        return addressRepository.findByUserId(userId);
    }


    public AddressRequest toDto(Address address) {
        AddressRequest dto = new AddressRequest();

        dto.setId(address.getId());
        dto.setUserId(address.getUserId());
        dto.setCountryCode(address.getCountryCode());

        dto.setAdmin1Id(address.getAdmin1Id());
        dto.setAdmin2Id(address.getAdmin2Id());
        dto.setAdmin3Id(address.getAdmin3Id());

        // Map names from Location repository
        if (address.getAdmin1Id() != null)
            dto.setAdmin1Name(locationRepository.findNameById(address.getAdmin1Id()));

        if (address.getAdmin2Id() != null)
            dto.setAdmin2Name(locationRepository.findNameById(address.getAdmin2Id()));

        if (address.getAdmin3Id() != null)
            dto.setAdmin3Name(locationRepository.findNameById(address.getAdmin3Id()));

        dto.setAddressLine1(address.getAddressLine1());
        dto.setAddressLine2(address.getAddressLine2());
        dto.setPostalCode(address.getPostalCode());
        dto.setCreatedAt(address.getCreatedAt());

        return dto;
    }



}
