package com.alex.catalog.facade.Impl;

import com.alex.catalog.facade.MachineFacade;
import com.alex.catalog.facade.MachinePartFacade;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.alex.catalog.dto.MachinePartDto;
import com.alex.catalog.entity.MachinePart;
import com.alex.catalog.entity.Manufacture;
import com.alex.catalog.error.NotFoundException;
import com.alex.catalog.mapper.MachinePartMapper;
import com.alex.catalog.service.MachinePartService;
import com.alex.catalog.service.ManufactureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.alex.catalog.error.constant.ErrorConstant.MANUFACTURE_NOT_FOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class MachinePartFacadeImpl implements MachinePartFacade{
    
    private final MachinePartMapper machinePartMapper;

    private final MachinePartService machinePartService;

    private final ManufactureService manufactureService;


    @Override
    public MachinePartDto create(MachinePartDto dto) {
        log.debug("create dto {}", dto);
        String manufactureName = dto.getManufacture();

        MachinePart entity = machinePartMapper.dtoToEntityConverter(dto);

        entity.setManufacture(manufactureService.findByName(manufactureName));
                
        MachinePart entitySaved = machinePartService.create(entity);

        log.debug("create entity {}", entitySaved);

        return machinePartMapper.entityToDtoConvert(entitySaved);
    }

    @Override
    public List<MachinePartDto> getAll() {
        List<MachinePart> entities = machinePartService.getAll();
        log.debug("getAll entities {}", entities);
        return machinePartMapper.entityToDtoListConvert(entities);
    }

    @Override
    public MachinePartDto getOne(Long id) {
        log.debug("getOne id {}", id);
        MachinePart entity = machinePartService.get(id);
        log.debug("getOne entity {}", entity);
        return machinePartMapper.entityToDtoConvert(entity);
    }

    @Override
    public Boolean delete(Long id) {
        log.debug("delete id {}", id);
        return machinePartService.delete(id);
    }

}
