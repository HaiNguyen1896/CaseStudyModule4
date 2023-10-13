package com.example.module4_shoesshop.Service.IService;

import java.util.List;

public interface IService<E> {
    E save(E e);

    E edit(E e);

    void delete(long id);

    E findById(long id);

    List<E> getAll();
}
