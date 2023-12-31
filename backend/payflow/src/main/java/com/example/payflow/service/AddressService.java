package com.example.payflow.service;

import com.example.payflow.dto.AddressDTO;
import com.example.payflow.model.Address;
import com.example.payflow.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public void saveAddress(AddressDTO addressDTO) {
        Address address = Address.builder().build();
        address.setApartmentNumber(addressDTO.getApartmentNumber());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setStreet(addressDTO.getStreet());
        address.setZipCode(addressDTO.getZipCode());
        addressRepository.save(address);
    }
}
