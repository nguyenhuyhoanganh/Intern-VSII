package com.example.base.controller;

import com.example.base.model.AuthDTO;
import com.example.base.model.ResponseDTO;
import com.example.base.model.TokenDTO;
import com.example.base.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HungDV
 * Class để test api với spring security jwt
 */

@RestController
@RequestMapping("/auth")
public class JwtProviderController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public ResponseDTO<TokenDTO> authenticateAndGetToken(@RequestBody AuthDTO authDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getAuthenticationCode())
        );
        if (authentication.isAuthenticated()) {
            return ResponseDTO.<TokenDTO>builder().data(jwtService.generateToken(authDTO.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
