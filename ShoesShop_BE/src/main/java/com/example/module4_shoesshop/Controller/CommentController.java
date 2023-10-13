package com.example.module4_shoesshop.Controller;

import com.example.module4_shoesshop.Service.IService.IAccountService;
import com.example.module4_shoesshop.Service.IService.ICommentService;
import com.example.module4_shoesshop.Service.IService.IProductService;
import com.example.module4_shoesshop.model.Account;
import com.example.module4_shoesshop.model.Comment;
import com.example.module4_shoesshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService iCommentService;
    @Autowired
    IProductService iProductService;
    @Autowired
    IAccountService iAccountService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> CommentList = iCommentService.getAll();
        if (CommentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(CommentList, HttpStatus.OK);
    }

    @PostMapping("/add/{comment}/{idP}")
    public ResponseEntity<Comment> addComment(@PathVariable String comment,@PathVariable long idP) {
        Product product = iProductService.findById(idP);
        String username="";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        Account account = iAccountService.findByUsername(username);
        Comment newComment = new Comment();
        LocalDate date = LocalDate.now();
        newComment.setCommentTime(date);
        newComment.setComment(comment);
        newComment.setProduct(product);
        newComment.setAccount(account);
        return new ResponseEntity<>(iCommentService.save(newComment), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable int id) {
        Comment currentComment = iCommentService.findById(id);
        if (currentComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentComment, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Comment> editComment(@RequestBody Comment Comment, @PathVariable int id) {
        Comment currentComment = iCommentService.findById(id);
        if (currentComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentComment.setComment(Comment.getComment());
        return new ResponseEntity<>(iCommentService.edit(currentComment), HttpStatus.OK);
    }



    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable int id) {
        if (iCommentService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iCommentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getCommentByProductID/{idP}")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable long idP) {
        List<Comment> CommentList = iCommentService.findCommentByProductID(idP);
        if (CommentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(CommentList, HttpStatus.OK);
    }
}
