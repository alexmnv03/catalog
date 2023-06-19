package com.alex.catalog.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alex.catalog.common.AbstractIdEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Машина - любой механизм, функционально законченное изделие, механизм, агрегат и т.д.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "machines", schema = "catalog_sh")
public class Machine extends AbstractIdEntity<Long> {

    @Column(name = "code", length = 17, nullable = false, unique = true)
    private String code;

    @Column(name = "model", length = 35, nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "image_file_name")
    private String imageFileName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "manufacture_id", referencedColumnName = "id", nullable = false)
    private Manufacture manufacture;
   
    @ManyToMany(    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(
        name = "machine_part_link",
        joinColumns = @JoinColumn(name = "machine_id"),
        inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private Set<MachinePart> machineParts = new HashSet<>();

    public void addMachine(MachinePart machinePart) {
        machineParts.add(machinePart);
        machinePart.getMachines().add(this);
    }
 
    public void removeTag(MachinePart machinePart) {
        machineParts.remove(machinePart);
        machinePart.getMachines().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return Objects.equals(code, machine.code);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

}
