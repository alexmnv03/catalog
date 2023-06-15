package com.alex.catalog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.alex.catalog.common.AbstractIdEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "manufacturers", schema = "catalog_sh")
public class Manufacture extends AbstractIdEntity<Long> {
    
    @Column(name = "manufacture_name", nullable = false, unique = true)
    private String manufactureName;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;
        
}
