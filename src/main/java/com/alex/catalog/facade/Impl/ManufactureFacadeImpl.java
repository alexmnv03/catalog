package com.alex.catalog.facade.Impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alex.catalog.dto.ManufactureDto;
import com.alex.catalog.entity.Manufacture;
import com.alex.catalog.facade.ManufactureFacade;
import com.alex.catalog.mapper.ManufactureMapper;
import com.alex.catalog.service.ManufactureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ManufactureFacadeImpl implements ManufactureFacade {
    
    private final ManufactureMapper manufactureMapper;

    private final ManufactureService manufactureService;

    @Override
    public ManufactureDto create(ManufactureDto dto) {
        log.debug("create dto {}", dto);
        Manufacture entity = manufactureService.create(manufactureMapper.dtoToEntityConverter(dto));
        log.debug("create entity {}", entity);
        return manufactureMapper.entityToDtoConvert(entity);
    }

    @Override
    public List<ManufactureDto> getAll() {
        List<Manufacture> entities = manufactureService.getAll();
        log.debug("getAll entities {}", entities);
        return manufactureMapper.entityToDtoListConvert(entities);
    }

    @Override
    public ManufactureDto getOne(Long id) {
        log.debug("getOne id {}", id);
        Manufacture entity = manufactureService.get(id);
        log.debug("getOne entity {}", entity);
        return manufactureMapper.entityToDtoConvert(entity);
    }

    @Override
    public Boolean delete(Long id) {
        log.debug("delete id {}", id);
        return manufactureService.delete(id);
    }    

}
