package com.example.module4_shoesshop.Repository;
import com.example.module4_shoesshop.model.Account;
import com.example.module4_shoesshop.model.OderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepo extends JpaRepository<Account,Long> {
    Account findByUsername(String username);
    @Query(nativeQuery = true,value = "select a.* from account a inner join role r on a.role_id = r.id where r.role_name='ROLE_USER'")
    List<Account> findAccountByRoleUser();
    @Query(nativeQuery = false,value = "select a from Account a where a.verificationCode=?1")
    public Account findByVerificationCode(String code);
    @Query("UPDATE Account a set a.isEnable=true where a.id=?1")
    @Modifying
    public void enable(long id);
}
