package com.alex.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alex.catalog.entity.MachinePart;

public interface MachinePartRepository extends JpaRepository<MachinePart, Long>{

    Optional<MachinePart> findByCode(String code);

    @Query("select mp from MachinePart mp join mp.machines m where m.code = :code")
    List<MachinePart> findMachinePartsWithCodeMachines(@Param("code") String code);
    
}
