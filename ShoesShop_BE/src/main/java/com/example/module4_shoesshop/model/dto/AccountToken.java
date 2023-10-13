package com.example.module4_shoesshop.model.dto;
import com.example.module4_shoesshop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountToken {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String username;
    private String token;
    private Role role;
}
