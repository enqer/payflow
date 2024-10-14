package com.example.payflow.controller;

import com.example.payflow.dto.LoanDTO;
import com.example.payflow.dto.LoanDTOPost;
import com.example.payflow.model.Loan;
import com.example.payflow.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class responsible for managing operations related to loans.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LoanController {

    /**
     * Service for handling loan operations.
     */
    private final LoanService loanService;

    /**
     * Retrieves a list of loans by account number ID using a HTTP GET request.
     *
     * @param id Account number ID for which to retrieve loans.
     * @return ResponseEntity with a list of {@link LoanDTO} objects if loans are found, else returns a 204 status.
     */
    @GetMapping("/numbers/{id}/loans")
    public ResponseEntity<List<LoanDTO>> getLoansByAccountNumberId(@PathVariable Long id){
        List<LoanDTO> loan = loanService.getLoansByAccountNumberId(id);
        if(!loan.isEmpty()){
            return ResponseEntity.ok().body(loan);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Retrieves a list of all loans by user ID using a HTTP GET request.
     *
     * @param id User ID for whom to retrieve all loans.
     * @return ResponseEntity with a list of {@link LoanDTO} objects if loans are found, else returns a 404 status.
     */
    @GetMapping("/users/{id}/numbers/loans")
    public ResponseEntity<List<LoanDTO>> getAllLoansByUserId(@PathVariable Long id){
        List<LoanDTO> loans = loanService.getAllLoansByUserId(id);
        if (loans != null)
            return ResponseEntity.status(HttpStatus.OK).body(loans);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Adds a new loan for a specific account number using a HTTP POST request.
     *
     * @param id   Account number ID for which to add the loan.
     * @param loan LoanDTOPost containing the details of the new loan.
     * @return ResponseEntity with the added {@link LoanDTO} if successful, else returns a 400 status.
     */
    @PostMapping("/numbers/{id}/loan")
    public ResponseEntity<LoanDTO> addLoan(@PathVariable Long id, @RequestBody LoanDTOPost loan) {
        LoanDTO l = loanService.addLoan(id,loan);
        if(l != null){
            return new ResponseEntity(l,HttpStatus.CREATED);
        }
        return new ResponseEntity("Incorrect data or account doesn't exist", HttpStatus.BAD_REQUEST);
    }

    /**
     * Removes a loan by ID using a HTTP DELETE request.
     *
     * @param id Loan ID to remove.
     * @return ResponseEntity with a success message if the loan is successfully removed, else returns a 400 status.
     */
    @DeleteMapping("/loan/{id}")
    public ResponseEntity<String> removeLoanById(@PathVariable Long id){
        Loan l = loanService.removeLoanById(id);
        if(l != null){
            return new ResponseEntity("Loan successfully removed.", HttpStatus.OK);
        }
        return new ResponseEntity("Loan doesn't exist.",HttpStatus.BAD_REQUEST);
    }
}
