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

import com.alex.catalog.dto.MachineDto;
import com.alex.catalog.dto.MachinePartDto;
import com.alex.catalog.facade.MachineFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Machine", description = "Methods for working with machine")
@RestController
@RequestMapping("/machine")
@RequiredArgsConstructor
public class MachineController {
    
    private final MachineFacade machineFacade;

    @PostMapping()
    @Operation(summary = "Create a machine")
    public MachineDto create(@RequestBody MachineDto dto) {
        log.debug("create dto {}", dto);
        return machineFacade.create(dto);
    }

    @PutMapping("/update")
    @Operation(summary = "Update information about a machine")
    public MachineDto update(@RequestBody MachineDto dto) {
        log.debug("update dto {}", dto);
        return machineFacade.update(dto);
    }    

    @Operation(summary = "Get list of all machines")
    @GetMapping()
    public List<MachineDto> getAll() {
        log.debug("getAll");
        return machineFacade.getAll();
    }

    @Operation(summary = "Get list of all machineParts by michine code")
    @GetMapping("/michine-part-code/{code}")
    public List<MachinePartDto> findMachinesWithCode(@PathVariable String code) {
        log.debug("getAll");
        return machineFacade.findMachinesWithCode(code);
    }    

    @Operation(summary = "Get information about a machine by id")
    @GetMapping("/{id}")
    public MachineDto getOne(@PathVariable Long id) {
        log.debug("getOne id {}", id);
        return machineFacade.getOne(id);
    }
    
    @Operation(summary = "Delete a machine")
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        log.debug("delete id {}", id);
        return machineFacade.delete(id);
    }

}
