package com.example.module4_shoesshop.Service.IService;

import com.example.module4_shoesshop.model.Account;
import com.example.module4_shoesshop.model.Comment;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentService extends IService<Comment>{
    List<Comment> findCommentByProductID(long id);
}
