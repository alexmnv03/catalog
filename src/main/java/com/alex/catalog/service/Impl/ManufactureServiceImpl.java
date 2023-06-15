package com.alex.catalog.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alex.catalog.entity.Manufacture;
import com.alex.catalog.error.NotFoundException;
import com.alex.catalog.perository.ManufactureRepository;
import com.alex.catalog.service.ManufactureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.alex.catalog.error.constant.ErrorConstant.MANUFACTURE_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ManufactureServiceImpl implements ManufactureService {

    private final ManufactureRepository manufactureRepository;

    @Override
    public Manufacture create(Manufacture object) {
        log.debug("create {}", object);
        return manufactureRepository.save(object);
    }

    @Override
    public void update(Manufacture object) {
        log.debug("update {}", object);
        manufactureRepository.save(object);
    }

    @Override
    public Manufacture get(Long id) {
        log.debug("get id {}", id);
        return manufactureRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(id, MANUFACTURE_NOT_FOUND));
    }

    @Override
    public List<Manufacture> getAll() {
        log.debug("getAll");
        return manufactureRepository.findAll();
    }    

    @Override
    public Manufacture findByName(String name) {
        log.info("findByName {}", name);
        Optional<Manufacture> manufacture = manufactureRepository.findByManufactureName(name);
        if (manufacture.isPresent()) {
            return manufacture.get();
        } else {
            throw new NotFoundException(name, MANUFACTURE_NOT_FOUND);
        }
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete, id {}", id);
        try {
            manufactureRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException exp) {
            log.error("Такая запись не найдена в БД.");
            return false;
        }
    }    
        
}
