package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.IRoleRepo;
import com.example.module4_shoesshop.Service.IService.IRoleService;
import com.example.module4_shoesshop.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepo iRoleRepo;
    @Override
    public Role save(Role role) {
        return iRoleRepo.save(role);
    }

    @Override
    public Role edit(Role role) {
        return iRoleRepo.save(role);
    }

    @Override
    public void delete(long id) {
        iRoleRepo.deleteById(id);
    }

    @Override
    public Role findById(long id) {
        return iRoleRepo.findById(id).get();
    }

    @Override
    public List<Role> getAll() {
        return iRoleRepo.findAll();
    }
}
