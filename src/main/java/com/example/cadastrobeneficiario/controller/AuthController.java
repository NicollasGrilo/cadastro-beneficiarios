package com.example.cadastrobeneficiario.controller;

import com.example.cadastrobeneficiario.model.AuthDTO;
import com.example.cadastrobeneficiario.model.LoginResponseDTO;
import com.example.cadastrobeneficiario.model.RegisterDTO;
import com.example.cadastrobeneficiario.repository.UserRepository;
import com.example.cadastrobeneficiario.services.TokenService;
import com.example.cadastrobeneficiario.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        var userPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken( (User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPass = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPass, data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
