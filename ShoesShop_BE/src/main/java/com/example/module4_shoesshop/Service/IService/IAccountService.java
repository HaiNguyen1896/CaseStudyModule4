package com.example.module4_shoesshop.Service.IService;
import com.example.module4_shoesshop.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IAccountService extends IService<Account>,UserDetailsService {
    Account findByUsername(String username);
    List<Account> findAccountByRoleUser();

    public Account registerAccount(Account account,String hash);
    public void sendVerificationEmail(Account account,String siteURL) throws MessagingException, UnsupportedEncodingException;

    boolean verify(String verificationCode);
}
