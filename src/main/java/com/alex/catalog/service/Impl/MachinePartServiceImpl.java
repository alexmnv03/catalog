package com.alex.catalog.service.Impl;

import com.alex.catalog.repository.MachinePartRepository;
import com.alex.catalog.service.MachinePartService;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.catalog.entity.MachinePart;
import com.alex.catalog.error.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.alex.catalog.error.constant.ErrorConstant.MACHINE_PART_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MachinePartServiceImpl implements MachinePartService{
    
    private final MachinePartRepository machinePartRepository;

    @Override
    public MachinePart create(MachinePart object) {
        log.debug("create {}", object);
        return machinePartRepository.save(object);
    }

    @Override
    public void update(MachinePart object) {
        log.debug("update {}", object);
        machinePartRepository.save(object);
    }

    @Override
    public MachinePart get(Long id) {
        log.debug("get id {}", id);
        return machinePartRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(id, MACHINE_PART_NOT_FOUND));        
    }

    @Override
    public List<MachinePart> getAll() {
        log.debug("getAll");
        return machinePartRepository.findAll();
    }

    @Override
    public List<MachinePart> findMachinePartsWithCodeMachines(String code) {
        log.debug("getAll");
        return machinePartRepository.findMachinePartsWithCodeMachines(code);
    }

    @Override
    public MachinePart findByCode(String code) {
        log.info("findByCode {}", code);
        Optional<MachinePart> machinePart = machinePartRepository.findByCode(code);
        if (machinePart.isPresent()) {
            return machinePart.get();
        } else {
            throw new NotFoundException(code, MACHINE_PART_NOT_FOUND);
        }
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete, id {}", id);
        try {
            machinePartRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException exp) {
            log.error("Такая запись не найдена в БД.");
            return false;
        }
    }    

}
