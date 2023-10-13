package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.IAccountService;
import com.example.module4_shoesshop.Utility.Utility;
import com.example.module4_shoesshop.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accountList = accountService.getAll();
        if (accountList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String hashPwt = passwordEncoder.encode(account.getPassword());
        Account registerAccount1 = accountService.registerAccount(account,hashPwt);
//        String siteURL = Utility.getSiteURL(request);
//        accountService.sendVerificationEmail(account,siteURL);
        return new ResponseEntity<>(accountService.save(registerAccount1),HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public void verifyAccount (@RequestParam(name="code") String verifyCode) {
        boolean verified = accountService.verify(verifyCode);
        String pageTitle = verified ? "success":"failed";
        System.out.println(pageTitle);
    }


    @GetMapping("/edit/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        Account currentAccount = accountService.findById(id);
        if (currentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentAccount, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Account> editAccount(@RequestBody Account account, @PathVariable int id) {
        Account currentAccount = accountService.findById(id);
        if (currentAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentAccount.setFirstName(account.getFirstName());
        currentAccount.setLastName(account.getLastName());
        currentAccount.setAddress(account.getAddress());
        currentAccount.setPhone(account.getPhone());
        currentAccount.setUsername(account.getUsername());
        currentAccount.setPassword(account.getPassword());
        currentAccount.setCountry(account.getCountry());
        currentAccount.setRole(account.getRole());
        return new ResponseEntity<>(accountService.edit(currentAccount), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable int id) {
        if (accountService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/checkByUsername/{username}")
    public ResponseEntity<Boolean> checkAccountByUsername(@PathVariable String username) {
        if (accountService.findByUsername(username) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
    }

    @GetMapping("/findAccountByRoleUser")
    public ResponseEntity<List<Account>> getAccountsByRole() {
        List<Account> accountList = accountService.findAccountByRoleUser();
        if (accountList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }
}
