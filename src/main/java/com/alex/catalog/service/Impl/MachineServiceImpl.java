package com.alex.catalog.service.Impl;

import com.alex.catalog.service.MachineService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.catalog.entity.Machine;
import com.alex.catalog.entity.MachinePart;
import com.alex.catalog.error.NotFoundException;
import com.alex.catalog.perository.MachineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.alex.catalog.error.constant.ErrorConstant.MACHINE_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MachineServiceImpl implements MachineService{
 
    private final MachineRepository machineRepository;

    @Override
    public Machine create(Machine object) {
        log.debug("create {}", object);
        return machineRepository.save(object);
    }

    @Override
    public void update(Machine object) {
        log.debug("update {}", object);
        machineRepository.save(object);
    }

    @Override
    public Machine get(Long id) {
        log.debug("get id {}", id);
        return machineRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(id, MACHINE_NOT_FOUND));        
    }

    @Override
    public List<MachinePart> findMachinesWithCode(String code) {
        log.debug("getAll");

        Machine machine = machineRepository.findByCode(code)
            .orElseThrow(() -> new NotFoundException(code, MACHINE_NOT_FOUND));  

        return new ArrayList<>(machine.getMachineParts());
    }

    @Override
    public List<Machine> getAll() {
        log.debug("getAll");
        return machineRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete, id {}", id);
        try {
            machineRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException exp) {
            log.error("Такая запись не найдена в БД.");
            return false;
        }
    }    
    
}
