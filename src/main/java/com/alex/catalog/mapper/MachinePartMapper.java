package com.alex.catalog.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.alex.catalog.dto.MachinePartDto;
import com.alex.catalog.entity.MachinePart;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MachinePartMapper {
    
    private final ModelMapper modelMapper;

    public MachinePartDto entityToDtoConvert(MachinePart machine) {
        return modelMapper.map(machine, MachinePartDto.class);
    }

    public MachinePart dtoToEntityConverter(MachinePartDto dto) {
        return modelMapper.map(dto, MachinePart.class);
    }
    
    public List<MachinePartDto> entityToDtoListConvert(List<MachinePart> listEntity) {
        List<MachinePartDto> listDto = new ArrayList<>();
        listEntity.forEach(entity ->
            listDto.add(entityToDtoConvert(entity)));
        return listDto;
    }

    public List<MachinePart> dtoToEntityListConvert(List<MachinePartDto> listDto) {
        List<MachinePart> listEntity = new ArrayList<>();
        listDto.forEach(dto ->
            listEntity.add(dtoToEntityConverter(dto)));
        return listEntity;
    }

}
