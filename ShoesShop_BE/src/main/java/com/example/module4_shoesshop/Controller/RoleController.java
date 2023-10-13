package com.example.module4_shoesshop.Controller;

import com.example.module4_shoesshop.Service.IService.IRoleService;
import com.example.module4_shoesshop.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService iRoleService;
    @GetMapping
    public ResponseEntity<List<Role>> getAllRole() {
        List<Role> RoleList = iRoleService.getAll();
        if (RoleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(RoleList, HttpStatus.OK);
    }
}
