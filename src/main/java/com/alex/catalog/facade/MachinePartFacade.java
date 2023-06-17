package com.alex.catalog.facade;

import java.util.List;

import com.alex.catalog.dto.MachinePartDto;

public interface MachinePartFacade {
    
    MachinePartDto create(MachinePartDto dto);

    List<MachinePartDto> getAll();

    MachinePartDto getOne(Long id);

    Boolean delete(Long id);
    
}
