package com.evandeemos.desafiopicpay.service;

import com.evandeemos.desafiopicpay.domain.wallet.Wallet;
import com.evandeemos.desafiopicpay.domain.wallet.WalletRepository;
import com.evandeemos.desafiopicpay.domain.wallet.dto.WalletDto;
import com.evandeemos.desafiopicpay.domain.wallet.dto.WalletUpdateDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Transactional
    public WalletUpdateDto addValueToWallet(WalletUpdateDto data) throws Exception {
        Wallet wallet = walletRepository.findById(data.id()).orElseThrow(() -> new Exception("Carteira não encontrada"));
        wallet.setMoney(wallet.getMoney().add(data.amount()));
        return new WalletUpdateDto(wallet);
    }

    @Transactional
    public WalletUpdateDto subtractValueFromWallet(WalletUpdateDto data) throws Exception {
        Wallet wallet = walletRepository.findById(data.id()).orElseThrow(() -> new Exception("Carteira não encontrada"));
        wallet.setMoney(wallet.getMoney().subtract(data.amount()));
        return new WalletUpdateDto(wallet);
    }

    public List<WalletDto> listAllWallets() {
        return walletRepository.findAll().stream().map(WalletDto::new).toList();
    }
}
