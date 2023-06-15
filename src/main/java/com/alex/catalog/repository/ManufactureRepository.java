package com.alex.catalog.repoository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.catalog.entity.Manufacture;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {

    Optional<Manufacture> findByManufactureName(String manufactureName);
    
}
