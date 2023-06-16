package com.alex.catalog.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.alex.catalog.dto.MachineDto;
import com.alex.catalog.entity.Machine;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MachineMapper {
    
    private final ModelMapper modelMapper;

    public MachineDto entityToDtoConvert(Machine machine) {
        return modelMapper.map(machine, MachineDto.class);
    }

    public Machine dtoToEntityConverter(MachineDto dto) {
        return modelMapper.map(dto, Machine.class);
    }
    
    public List<MachineDto> entityToDtoListConvert(List<Machine> listEntity) {
        List<MachineDto> listDto = new ArrayList<>();
        listEntity.forEach(entity ->
            listDto.add(entityToDtoConvert(entity)));
        return listDto;
    }

    public List<Machine> dtoToEntityListConvert(List<MachineDto> listDto) {
        List<Machine> listEntity = new ArrayList<>();
        listDto.forEach(dto ->
            listEntity.add(dtoToEntityConverter(dto)));
        return listEntity;
    }

}
