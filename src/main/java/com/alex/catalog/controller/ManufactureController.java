package com.alex.catalog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.catalog.dto.ManufactureDto;
import com.alex.catalog.facade.ManufactureFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Manufacture", description = "Methods for working with manufacture")
@RestController
@RequestMapping("/manufacture")
@RequiredArgsConstructor
public class ManufactureController {
    
    private final ManufactureFacade manufactureFacade;

    @PostMapping()
    @Operation(summary = "Create a manufacture")
    public ManufactureDto create(@RequestBody ManufactureDto dto) {
        log.debug("create dto {}", dto);
        return manufactureFacade.create(dto);
    }

    @PutMapping("/update")
    @Operation(summary = "Update information about a manufacture")
    public ManufactureDto update(@RequestBody ManufactureDto dto) {
        log.debug("update dto {}", dto);
        return manufactureFacade.create(dto);
    }    

    @Operation(summary = "Get list of all manufactures")
    @GetMapping()
    public List<ManufactureDto> getAll() {
        log.debug("getAll");
        return manufactureFacade.getAll();
    }

    @Operation(summary = "Get information about a manufacture by id")
    @GetMapping("/{id}")
    public ManufactureDto getOne(@PathVariable Long id) {
        log.debug("getOne id {}", id);
        return manufactureFacade.getOne(id);
    }
    
    @Operation(summary = "Delete a manufacture")
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        log.debug("delete id {}", id);
        return manufactureFacade.delete(id);
    }    

}
