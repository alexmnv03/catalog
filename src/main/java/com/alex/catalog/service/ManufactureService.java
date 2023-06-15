package com.alex.catalog.service;

import java.util.List;
import java.util.Optional;

import com.alex.catalog.entity.Manufacture;

public interface ManufactureService {
    
    Manufacture create(Manufacture object);

    void update(Manufacture object);

    Manufacture get(Long id);

    List<Manufacture> getAll();

    Manufacture findByName(String name);

    boolean delete(Long id);

}
