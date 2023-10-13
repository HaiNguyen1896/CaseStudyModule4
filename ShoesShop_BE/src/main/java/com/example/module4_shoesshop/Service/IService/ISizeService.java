package com.example.module4_shoesshop.Service.IService;

import com.example.module4_shoesshop.model.Product;
import com.example.module4_shoesshop.model.Size;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISizeService extends IService<Size>{
    List<Size> findSizesByProductId(long id);

}
