package com.example.module4_shoesshop.Controller;

import com.example.module4_shoesshop.Service.IService.ICustomerInfoService;
import com.example.module4_shoesshop.model.Account;
import com.example.module4_shoesshop.model.CustomerInfo;
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
@RequestMapping("/customerInfo")
public class CustomerInfoController {
    @Autowired
    ICustomerInfoService iCustomerInfoService;
    @GetMapping
    public ResponseEntity<List<CustomerInfo>> getAllCustomerInfo() {
        List<CustomerInfo> customerInfos = iCustomerInfoService.getAll();
        if (customerInfos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerInfos, HttpStatus.OK);
    }


}
