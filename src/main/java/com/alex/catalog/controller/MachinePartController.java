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

import com.alex.catalog.dto.MachinePartDto;
import com.alex.catalog.facade.MachinePartFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Machine parts", description = "Methods for working with machine parts")
@RestController
@RequestMapping("/machine-part")
@RequiredArgsConstructor
public class MachinePartController {
    
    private final MachinePartFacade machinePartFacade;

    @PostMapping()
    @Operation(summary = "Create a machine parts")
    public MachinePartDto create(@RequestBody MachinePartDto dto) {
        log.debug("create dto {}", dto);
        return machinePartFacade.create(dto);
    }

    @PutMapping("/update")
    @Operation(summary = "Update information about a machine parts")
    public MachinePartDto update(@RequestBody MachinePartDto dto) {
        log.debug("update dto {}", dto);
        return machinePartFacade.create(dto);
    }    

    @Operation(summary = "Get list of all machines parts")
    @GetMapping()
    public List<MachinePartDto> getAll() {
        log.debug("getAll");
        return machinePartFacade.getAll();
    }

    @Operation(summary = "Get information about a machine parts by id")
    @GetMapping("/{id}")
    public MachinePartDto getOne(@PathVariable Long id) {
        log.debug("getOne id {}", id);
        return machinePartFacade.getOne(id);
    }
    
    @Operation(summary = "Delete a machine parts")
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        log.debug("delete id {}", id);
        return machinePartFacade.delete(id);
    }

}
