package com.evandeemos.desafiopicpay.service;

import com.evandeemos.desafiopicpay.domain.user.User;
import com.evandeemos.desafiopicpay.domain.user.UserRepository;
import com.evandeemos.desafiopicpay.domain.user.dto.SuccefullyCreatedUserDto;
import com.evandeemos.desafiopicpay.domain.user.dto.UserCreationDto;
import com.evandeemos.desafiopicpay.domain.user.dto.UserDetailsDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public SuccefullyCreatedUserDto createUser(UserCreationDto data) {
        User user = new User(data);
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
