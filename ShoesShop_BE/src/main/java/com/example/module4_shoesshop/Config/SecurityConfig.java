package com.example.module4_shoesshop.Config;

import com.example.module4_shoesshop.Service.IService.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
    private IAccountService iAccountService;
    @Autowired
    private FilterAuthToken filterAuthToken;
    @Autowired
    private AuthenticationBCrypt authenticationBCrypt;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationProvider AuthenticationBCrypt;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // cấu hình xác thực
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iAccountService).passwordEncoder(passwordEncoder);
//        auth.authenticationProvider(AuthenticationBCrypt);
    }

    // cấu hình phân quyền
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login","/account/add","/account/verify","/account/checkByUsername/**").permitAll()
                .and().authorizeRequests().antMatchers("/user**", "/images","/category","/products").hasAnyRole("USER", "ADMIN")
                .and().authorizeRequests().antMatchers(
                        "/account**","/admin**", "/category**","/country**",  "/images**","/role**","/products**").hasRole("ADMIN")
                .and().authorizeRequests().anyRequest().authenticated()
                .and().logout();
        http.csrf().disable();
        http.addFilterBefore(filterAuthToken, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
    }

}
