package com.evandeemos.desafiopicpay.controller;

import com.evandeemos.desafiopicpay.domain.user.dto.SuccefullyCreatedUserDto;
import com.evandeemos.desafiopicpay.domain.user.dto.UserCreationDto;
import com.evandeemos.desafiopicpay.domain.user.dto.UserDetailsDto;
import com.evandeemos.desafiopicpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<SuccefullyCreatedUserDto> createUser(
            @RequestBody @Valid UserCreationDto userData,
            UriComponentsBuilder uriComponentsBuilder) {
        SuccefullyCreatedUserDto user = userService.createUser(userData);
        URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDetailsDto>> listAllUsers() {
        return ResponseEntity.ok(userService.listAllUsers());
    }
}
