package com.projetos.convenios.controller;

import com.projetos.convenios.domain.dto.auth.LoginRequestDTO;
import com.projetos.convenios.domain.dto.auth.LoginResponseDTO;
import com.projetos.convenios.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO dto) {

        return ResponseEntity.ok(authService.login(dto));
    }
}

