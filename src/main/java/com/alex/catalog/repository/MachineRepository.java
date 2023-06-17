package com.alex.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alex.catalog.entity.Machine;
import com.alex.catalog.entity.MachinePart;

public interface MachineRepository extends JpaRepository<Machine, Long>{

    // @Query("select m.machineParts from Machine m where m.code = :code")
    // List<MachinePart> findMachinesWithCode(@Param("code") String code);    

    Optional<Machine> findByCode(String code);

}
