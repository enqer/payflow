package com.example.payflow.service;


import com.example.payflow.model.ExchangeRate;
import com.example.payflow.repository.ExchangeRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;


@AllArgsConstructor
@Service
public class ExchangeRateService {

    private final ExchangeRateRepository repository;


    public List<ExchangeRate> getExchangeRates(int last) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return repository.findAll()
                .stream()
                .sorted(Comparator.nullsLast((r1, r2) -> {
                    try {
                        return formatter.parse(r2.getDate()).compareTo(formatter.parse(r1.getDate()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })).limit(last).toList();
    }

}
