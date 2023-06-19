package com.alex.catalog.facade.Impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alex.catalog.dto.MachineDto;
import com.alex.catalog.dto.MachinePartDto;
import com.alex.catalog.entity.Machine;
import com.alex.catalog.entity.MachinePart;
import com.alex.catalog.facade.MachineFacade;
import com.alex.catalog.mapper.MachineMapper;
import com.alex.catalog.mapper.MachinePartMapper;
import com.alex.catalog.service.MachinePartService;
import com.alex.catalog.service.MachineService;
import com.alex.catalog.service.ManufactureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MachineFacadeImpl implements MachineFacade{
    
    private final MachineMapper machineMapper;

    private final MachinePartMapper machinePartMapper;

    private final MachineService machineService;

    private final MachinePartService machinePartService;

    private final ManufactureService manufactureService;

    @Override
    public MachineDto create(MachineDto dto) {
        log.debug("create dto {}", dto);
        String manufactureName = dto.getManufacture();
        String codeMachinePart = dto.getCodeMachinePart();

        log.debug("manufactureName {}", manufactureName);
        log.debug("codeMachinePart {}", codeMachinePart);
        
        Machine entity = machineMapper.dtoToEntityConverter(dto);
        entity.setManufacture(manufactureService.findByName(manufactureName));
        Machine entitySaved;

        if (codeMachinePart != null) {
            MachinePart machinePart = machinePartService.findByCode(codeMachinePart);
            log.debug("MachinePart id {}", machinePart.getId());
            entity.addMachine(machinePart);                        
            entitySaved = machineService.create(entity);
            // machinePart.getMachines().add(entitySaved);
            // log.debug("machinePart id {}", machinePart.getId());
            // machinePartService.create(machinePart);
        } else {
            entitySaved = machineService.create(entity);
        }
        log.debug("create entity {}", entitySaved);
        return machineMapper.entityToDtoConvert(entitySaved);
    }

    @Override
    public MachineDto update(MachineDto dto) {
        log.debug("update dto {}", dto);
        String manufactureName = dto.getManufacture();
        String codeMachinePart = dto.getCodeMachinePart();

        log.debug("manufactureName {}", manufactureName);
        log.debug("codeMachinePart {}", codeMachinePart);
        
        Machine entity = machineMapper.dtoToEntityConverter(dto);
        entity.setManufacture(manufactureService.findByName(manufactureName));
        entity.setMachineParts(machineService.get(entity.getId()).getMachineParts());
        Machine entitySaved;

        if (codeMachinePart != null) {
            MachinePart machinePart = machinePartService.findByCode(codeMachinePart);
            log.debug("MachinePart id {}", machinePart.getId());
            entity.addMachine(machinePart);
                        
            entitySaved = machineService.create(entity);
            log.debug("entity id {}", entity.getId());
            log.debug("entitySaved id {}", entitySaved.getId());

            // machinePart.getMachines().add(entitySaved);
            // log.debug("machinePart id {}", machinePart.getId());
            // machinePartService.create(machinePart);
        } else {
            entitySaved = machineService.create(entity);
        }
        log.debug("create entity {}", entitySaved);
        return machineMapper.entityToDtoConvert(entitySaved);
    }    

    @Override
    public List<MachineDto> getAll() {
        List<Machine> entities = machineService.getAll();
        log.debug("getAll entities {}", entities);
        return machineMapper.entityToDtoListConvert(entities);
    }

    @Override
    public List<MachinePartDto> findMachinesWithCode(String code) {
        log.debug("findMachinesWithCode code {}", code);
        List<MachinePart> machinePart = machineService.findMachinesWithCode(code);
        log.debug("parts size{}", machinePart.size());
        return machinePartMapper.entityToDtoListConvert(machinePart);
    }

    @Override
    public MachineDto getOne(Long id) {
        log.debug("getOne id {}", id);
        Machine entity = machineService.get(id);
        log.debug("getOne entity {}", entity);
        return machineMapper.entityToDtoConvert(entity);
    }

    @Override
    public Boolean delete(Long id) {
        log.debug("delete id {}", id);
        return machineService.delete(id);
    }

}
