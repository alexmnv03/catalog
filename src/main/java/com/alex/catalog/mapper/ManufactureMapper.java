package com.alex.catalog.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.alex.catalog.dto.ManufactureDto;
import com.alex.catalog.entity.Manufacture;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ManufactureMapper {
 
    private final ModelMapper modelMapper;

    public ManufactureDto entityToDtoConvert(Manufacture manufacture) {
        return modelMapper.map(manufacture, ManufactureDto.class);
    }

    public Manufacture dtoToEntityConverter(ManufactureDto dto) {
        return modelMapper.map(dto, Manufacture.class);
    }
    
    public List<ManufactureDto> entityToDtoListConvert(List<Manufacture> listEntity) {
        List<ManufactureDto> listDto = new ArrayList<>();
        listEntity.forEach(entity ->
            listDto.add(entityToDtoConvert(entity)));
        return listDto;
    }

    public List<Manufacture> dtoToEntityListConvert(List<ManufactureDto> listDto) {
        List<Manufacture> listEntity = new ArrayList<>();
        listDto.forEach(dto ->
            listEntity.add(dtoToEntityConverter(dto)));
        return listEntity;
    }

}
