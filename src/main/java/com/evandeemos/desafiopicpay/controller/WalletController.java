package com.evandeemos.desafiopicpay.controller;

import com.evandeemos.desafiopicpay.domain.wallet.dto.WalletDto;
import com.evandeemos.desafiopicpay.domain.wallet.dto.WalletUpdateDto;
import com.evandeemos.desafiopicpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping
    public ResponseEntity<List<WalletDto>> listAllWallets() {
        return ResponseEntity.ok(walletService.listAllWallets());
    }

    @PostMapping("/add")
    public ResponseEntity<WalletUpdateDto> addValueToWallet(@RequestBody @Valid WalletUpdateDto data) throws Exception {
        return ResponseEntity.ok(walletService.addValueToWallet(data));
    }
}
