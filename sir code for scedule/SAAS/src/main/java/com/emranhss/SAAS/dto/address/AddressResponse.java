package com.emranhss.SAAS.dto.address;

public record AddressResponse(
        Long id,
        String type,
        String addressLine1,
        String addressLine2,
        Long countryId,
        String countryName,
        Long stateId,
        String stateName,
        Long cityId,
        String cityName,
        String postalCode
) {

}

