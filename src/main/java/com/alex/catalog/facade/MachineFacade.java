package com.alex.catalog.facade;

import java.util.List;

import com.alex.catalog.dto.MachineDto;
import com.alex.catalog.dto.MachinePartDto;

public interface MachineFacade {

    MachineDto create(MachineDto dto);
    
    MachineDto update(MachineDto dto);

    List<MachineDto> getAll();

    List<MachinePartDto> findMachinesWithCode(String code);

    MachineDto getOne(Long id);
    
    Boolean delete(Long id);
      
}
