package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.IOderDetailRepo;
import com.example.module4_shoesshop.Service.IService.IOderDetailService;
import com.example.module4_shoesshop.model.OderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OderDetailService implements IOderDetailService {
    @Autowired
    IOderDetailRepo iOderDetailRepo;
    @Override
    public OderDetail save(OderDetail oderDetail) {
        return iOderDetailRepo.save(oderDetail) ;
    }

    @Override
    public OderDetail edit(OderDetail oderDetail) {
        return iOderDetailRepo.save(oderDetail);
    }

    @Override
    public void delete(long id) {
        iOderDetailRepo.deleteById(id);
    }

    @Override
    public OderDetail findById(long id) {
        return iOderDetailRepo.findById(id).get();
    }

    @Override
    public List<OderDetail> getAll() {
        return iOderDetailRepo.findAll();
    }

    @Override
    public List<OderDetail> findOderDetailByOderID(long id) {
        return iOderDetailRepo.findOderDetailByOderID(id);
    }
}
