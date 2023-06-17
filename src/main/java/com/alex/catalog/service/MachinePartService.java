package com.alex.catalog.service;

import java.util.List;

import com.alex.catalog.entity.MachinePart;

public interface MachinePartService {
    
    MachinePart create(MachinePart object);

    void update(MachinePart object);

    MachinePart get(Long id);

    MachinePart findByCode(String code);

    List<MachinePart> getAll();

    List<MachinePart> findMachinePartsWithCodeMachines(String code);

    boolean delete(Long id);
    
}
