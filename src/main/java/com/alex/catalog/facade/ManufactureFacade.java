package com.alex.catalog.facade;

import java.util.List;

import com.alex.catalog.dto.ManufactureDto;


public interface ManufactureFacade {

    ManufactureDto create(ManufactureDto dto);

    List<ManufactureDto> getAll();

    ManufactureDto getOne(Long id);

    Boolean delete(Long id);

}
