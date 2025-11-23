package com.projetos.convenios.controller.mapper;

import com.projetos.convenios.domain.Address;
import com.projetos.convenios.domain.dto.address.AddressDTO;

public class AddressMapper {
    public static AddressDTO mapper(Address src) {
        return AddressDTO.builder()
                .city(src.getCity())
                .street(src.getStreet())
                .zip(src.getZip())
                .country(src.getCountry())
                .state(src.getState())
                .build();
    }
}
