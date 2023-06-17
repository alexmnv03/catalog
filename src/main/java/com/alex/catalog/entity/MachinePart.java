package com.alex.catalog.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alex.catalog.common.AbstractIdEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Блок, узел, любая функционально законченная часть машины
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "machine_parts", schema = "catalog_sh")
public class MachinePart extends AbstractIdEntity<Long> {
    
    @Column(name = "name_parts", length = 100, nullable = false)
    private String nameParts;

    @Column(length = 17, nullable = false, unique = true)
    private String code;

    /* Если флаг = true, то это OEM, иначе аналог. */
    @Column(name = "oem_or_analog", nullable = false)
    private Boolean oemOrAnalog;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(length = 15, nullable = false)
    private String version;

    @Column(name = "image_file_name")
    private String imageFileName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "manufacture_id", referencedColumnName = "id", nullable = false)
    private Manufacture manufacture;


    @ManyToMany(
        // fetch = FetchType.LAZY,
    mappedBy = "machineParts")
    private Set<Machine> machines = new HashSet<>();

    @Column(name = "price_part")
    private PricePart pricePart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MachinePart machinePart = (MachinePart) o;
        return Objects.equals(code, machinePart.code);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
