package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.IOderDetailService;
import com.example.module4_shoesshop.model.OderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/oderDetail")
public class OderDetailController {
    @Autowired
    IOderDetailService iOderDetailService;
    @GetMapping
    public ResponseEntity<List<OderDetail>> getAllOderDetail() {
        List<OderDetail> oderDetailList = iOderDetailService.getAll();
        if (oderDetailList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(oderDetailList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OderDetail> addOder(@RequestBody OderDetail oderDetail) {
        return new ResponseEntity<>(iOderDetailService.save(oderDetail), HttpStatus.CREATED);
    }

    @GetMapping("/getOder/{id}")
    public ResponseEntity<List<OderDetail>> getOderDetailByOderID(@PathVariable long id) {
        return new ResponseEntity<>(iOderDetailService.findOderDetailByOderID(id), HttpStatus.CREATED);
    }
}
