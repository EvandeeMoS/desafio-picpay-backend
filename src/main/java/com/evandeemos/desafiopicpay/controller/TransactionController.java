package com.evandeemos.desafiopicpay.controller;

import com.evandeemos.desafiopicpay.domain.transaction.dto.SuccefullyCreatedTransactionDto;
import com.evandeemos.desafiopicpay.domain.transaction.dto.TransactionCreationDto;
import com.evandeemos.desafiopicpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<SuccefullyCreatedTransactionDto> createTransaction(@RequestBody TransactionCreationDto data) throws Exception {
        SuccefullyCreatedTransactionDto result = transactionService.createTransaction(data);
        return ResponseEntity.ok(result);
    }
}
