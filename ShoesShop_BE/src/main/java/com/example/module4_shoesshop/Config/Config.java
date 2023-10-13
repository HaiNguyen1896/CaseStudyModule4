package com.example.module4_shoesshop.Config;


import com.example.module4_shoesshop.model.dto.CustomerInfoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public List<CustomerInfoDTO> customerInfoDTOList() {
        return new ArrayList<>();
    }
}
