package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.IOderService;
import com.example.module4_shoesshop.model.Oder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/oder")
public class OderController {
    @Autowired
    IOderService iOderService;
    @GetMapping
    public ResponseEntity<List<Oder>> getAllOder() {
        List<Oder> OderList = iOderService.getAll();
        if (OderList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(OderList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Oder> addOder(@RequestBody Oder Oder) {
        return new ResponseEntity<>(iOderService.save(Oder), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Oder> getOder(@PathVariable int id) {
        Oder currentOder = iOderService.findById(id);
        if (currentOder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentOder, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Oder> editOder(@RequestBody Oder Oder, @PathVariable int id) {
        Oder currentOder = iOderService.findById(id);
        if (currentOder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentOder.setQuantity(Oder.getQuantity());
        currentOder.setTotalPrice(Oder.getTotalPrice());
        currentOder.setDate(Oder.getDate());
        currentOder.setAccount(Oder.getAccount());
        return new ResponseEntity<>(iOderService.edit(currentOder), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Oder> deleteOder(@PathVariable int id) {
        if (iOderService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iOderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
