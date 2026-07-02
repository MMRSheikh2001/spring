package com.emranhss.SAAS.controller;


import com.emranhss.SAAS.dto.address.AddressRequest;
import com.emranhss.SAAS.dto.address.AddressResponse;
import com.emranhss.SAAS.entity.Address;
import com.emranhss.SAAS.repository.AddressRepository;
import com.emranhss.SAAS.repository.LocationRepository;
import com.emranhss.SAAS.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private LocationRepository locationRepository;

    @PostMapping
    public ResponseEntity<Address> save(
            @Valid @RequestBody AddressRequest request) {
        return ResponseEntity.ok(addressService.save(request));
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Address>> getByUser(@PathVariable Long userId) {
//        return ResponseEntity.ok(addressService.getByUser(userId));
//    }



    @GetMapping("/user/{userId}")
    public List<AddressRequest> getAddressesByUser(@PathVariable Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    private AddressRequest toDto(Address address) {
        AddressRequest dto = new AddressRequest();

        dto.setId(address.getId());
        dto.setUserId(address.getUserId());
        dto.setCountryCode(address.getCountryCode());

        dto.setAdmin1Id(address.getAdmin1Id());
        dto.setAdmin2Id(address.getAdmin2Id());
        dto.setAdmin3Id(address.getAdmin3Id());

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
