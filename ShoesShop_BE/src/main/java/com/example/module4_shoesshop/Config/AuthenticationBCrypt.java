package com.example.module4_shoesshop.Config;

import com.example.module4_shoesshop.Service.IService.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component

public class AuthenticationBCrypt implements AuthenticationProvider {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        UserDetails userDetails1 = iAccountService.loadUserByUsername(username);
        if (userDetails1 != null) {
            if (passwordEncoder.matches(pwd, userDetails1.getPassword())) {
                UsernamePasswordAuthenticationToken authentication1 = new UsernamePasswordAuthenticationToken(
                        userDetails1, userDetails1.getPassword(), userDetails1.getAuthorities());
                return authentication1;
            }
        } else {
            throw new BadCredentialsException("Invalid password");
        }
        throw new BadCredentialsException("No user registed with this details");
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
