package com.alex.catalog.service;

import java.util.List;

import com.alex.catalog.entity.Machine;
import com.alex.catalog.entity.MachinePart;

public interface MachineService {

    Machine create(Machine object);

    void update(Machine object);

    Machine get(Long id);

    List<Machine> getAll();

    List<MachinePart> findMachinesWithCode(String code);
    
    boolean delete(Long id);
}
