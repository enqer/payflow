package com.example.payflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    Long id;
    String cardNumber;
    LocalDate validDate;
    String cvv;
}

