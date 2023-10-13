package com.example.module4_shoesshop.Repository;

import com.example.module4_shoesshop.model.Account;
import com.example.module4_shoesshop.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
    @Query(nativeQuery = true,value = "select c.* from comment c where c.product_id=:id")
    List<Comment> findCommentByProductID(@Param("id") long id);
}
