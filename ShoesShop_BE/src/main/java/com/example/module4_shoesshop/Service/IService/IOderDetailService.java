package com.example.module4_shoesshop.Service.IService;

import com.example.module4_shoesshop.model.OderDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOderDetailService extends IService<OderDetail>{
    List<OderDetail> findOderDetailByOderID(@Param("id") long id);
}
