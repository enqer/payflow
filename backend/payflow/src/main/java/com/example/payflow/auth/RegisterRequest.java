package com.example.payflow.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String country;
    private String email;
    private String phoneNumber;
    private String zipCode;
    private String city;
    private String street;
    private String homeNumber;
    private String apartmentNumber;
    private String countryAddress;
    private String zipCodeCorrespondence;
    private String cityCorrespondence;
    private String streetCorrespondence;
    private String homeNumberCorrespondence;
    private String apartmentNumberCorrespondence;
    private String countryAddressCorrespondence;
    private String accountType;
//    private String login;
    private String password;

}
