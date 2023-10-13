package com.example.module4_shoesshop.Controller;

import com.example.module4_shoesshop.Service.IService.IAccountService;
import com.example.module4_shoesshop.model.Account;
import com.example.module4_shoesshop.model.dto.AccountToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Account existingAccount = iAccountService.findByUsername(account.getUsername());
        if (existingAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tai khoan và Mat khau");
        }
        if (passwordEncoder.matches(account.getPassword(), existingAccount.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = createToken(account.getUsername());
            return ResponseEntity.ok(new AccountToken(existingAccount.getId(), existingAccount.getFirstName(), existingAccount.getLastName(), existingAccount.getAddress(), existingAccount.getPhone(), existingAccount.getUsername(), token, existingAccount.getRole()));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    public static final String PRIVATE_KEY = "abc1234567890xyz";
    private static final long EXPIRE_TIME = 86400L;

    // hàm tạo ra token
    public String createToken(String username) {
        return Jwts.builder()
                .setIssuer("ShoesShop")
                .setSubject((username))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000)) // Thời gian tồn tại token
                .signWith(SignatureAlgorithm.HS512, PRIVATE_KEY) //Thuật toán
                .compact(); //Thực hiện tạo token
    }
}
