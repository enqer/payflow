package com.example.payflow.controller;


import com.example.payflow.dto.AccountNumberDTO;
import com.example.payflow.model.AccountNumber;
import com.example.payflow.service.AccountNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AccountNumberController {
    private final AccountNumberService accountNumberService;
    @GetMapping("/numbers")
    public List<AccountNumber> getAccountNumbers() {
        return accountNumberService.getAccountNumbers();
    }
    @GetMapping("/users/{id}/numbers")
    public List<AccountNumberDTO> getAccountNumberByUserId(@PathVariable Long id){
        return accountNumberService.getAccountNumberByUserId(id);
    }
    @PostMapping("/number")
    public ResponseEntity<AccountNumberDTO> addAccount(@RequestBody AccountNumber accountNumber){
        return accountNumberService.addAccount(accountNumber);
    }
}
