package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.IAccountRepo;
import com.example.module4_shoesshop.Service.IService.IAccountService;
import com.example.module4_shoesshop.model.Account;
import com.example.module4_shoesshop.model.Country;
import com.example.module4_shoesshop.model.Role;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
@Service
public class AccountService implements IAccountService {

    IAccountRepo iAccountRepo;
    private JavaMailSender mailSender;
    @Autowired
    public AccountService(IAccountRepo iAccountRepo, JavaMailSender mailSender) {
        this.iAccountRepo = iAccountRepo;
        this.mailSender = mailSender;
    }

    public AccountService(IAccountRepo iAccountRepo) {
        this.iAccountRepo = iAccountRepo;
    }

    @Override
    public Account save(Account account) {
        return iAccountRepo.save(account);
    }
    @Override
    public void sendVerificationEmail(Account account,String siteURL) throws MessagingException, UnsupportedEncodingException {
        String subject = "please verify your registration";
        String senderName = "King Shoes Shop";
        String verifyURL = siteURL+"/account/verify?code="+account.getVerificationCode();
        String mailContent = "<p>Dear "+account.getFirstName()+" "+account.getLastName()+",</p>" +
                "<p>Plear click here to "+"<a href=\""+verifyURL+ "\">Verify</a> your account</p>" +
                "<p>Thanks you!!!</p>" +
                "<p>The King Shoes Shop</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("hainguyennb01@gmail.com",senderName);
        helper.setTo(account.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        mailSender.send(message);
    }

    @Override
    @Transactional
    public boolean verify(String verificationCode) {
        Account account = iAccountRepo.findByVerificationCode(verificationCode);
        if(account==null || account.isEnable()) {
            return false;
        }else{
            iAccountRepo.enable(account.getId());
            return true;
        }
    }

    @Override
    public Account registerAccount(Account account,String hash) {
        account.setPassword(hash);
        Country country = new Country();
        country.setId(1);
        account.setCountry(country);
        Role role = new Role();
        role.setId(1);
        account.setRole(role);
        account.setEnable(false);
        account.setEmail("test123@gmail.com");
        String randomCode = RandomString.make(64);
        account.setVerificationCode(randomCode);
        return account;
    }


    @Override
    public Account edit(Account account) {
        return iAccountRepo.save(account);
    }

    @Override
    public void delete(long id) {
        iAccountRepo.deleteById(id);
    }

    @Override
    public Account findById(long id) {
        return iAccountRepo.findById(id).get();
    }

    @Override
    public List<Account> getAll() {
        return iAccountRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.findByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(account.getRole());
        return new User(username, account.getPassword(), roles);
    }

    @Override
    public Account findByUsername(String username) {
        return iAccountRepo.findByUsername(username);
    }

    @Override
    public List<Account> findAccountByRoleUser() {
        return iAccountRepo.findAccountByRoleUser();
    }



}
