package com.practice.authority.service;

import com.practice.authority.entity.UserAuthority;
import com.practice.authority.repository.UserAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserAuthorityRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public String saveUser(UserAuthority userAuthority) {
        userAuthority.setPassword(passwordEncoder.encode(userAuthority.getPassword()));
        repository.save(userAuthority);
        return "user added to the system";
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
