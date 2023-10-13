package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.ICommentRepo;
import com.example.module4_shoesshop.Service.IService.ICommentService;
import com.example.module4_shoesshop.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepo iCommentRepo;
    @Override
    public Comment save(Comment comment) {
        return iCommentRepo.save(comment);
    }

    @Override
    public Comment edit(Comment comment) {
        return iCommentRepo.save(comment);
    }

    @Override
    public void delete(long id) {
        iCommentRepo.deleteById(id);
    }

    @Override
    public Comment findById(long id) {
        return iCommentRepo.findById(id).get();
    }

    @Override
    public List<Comment> getAll() {
        return iCommentRepo.findAll();
    }

    @Override
    public List<Comment> findCommentByProductID(long id) {
        return iCommentRepo.findCommentByProductID(id);
    }
}
