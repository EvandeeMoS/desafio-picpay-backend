package com.evandeemos.desafiopicpay.service;

import com.evandeemos.desafiopicpay.domain.user.User;
import com.evandeemos.desafiopicpay.domain.user.UserRepository;
import com.evandeemos.desafiopicpay.domain.user.dto.SuccefullyCreatedUserDto;
import com.evandeemos.desafiopicpay.domain.user.dto.UserCreationDto;
import com.evandeemos.desafiopicpay.domain.user.dto.UserDetailsDto;
import com.evandeemos.desafiopicpay.domain.wallet.Wallet;
import com.evandeemos.desafiopicpay.domain.wallet.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public SuccefullyCreatedUserDto createUser(UserCreationDto data) {
        User user = new User(data);
        Wallet wallet = new Wallet();
        walletRepository.save(wallet);
        user.setWallet(wallet);
        repository.save(user);
        return new SuccefullyCreatedUserDto(user);
    }

    public UserDetailsDto findUserById(long id) throws Exception {
        User user = repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
        return new UserDetailsDto(user);
    }

    public List<UserDetailsDto> listAllUsers() {
        return repository.findAll().stream().map(UserDetailsDto::new).toList();
    }

}
