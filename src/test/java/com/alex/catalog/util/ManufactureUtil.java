package com.alex.catalog.util;

import com.alex.catalog.dto.ManufactureDto;
import com.alex.catalog.entity.Manufacture;

public final class ManufactureUtil {

    public static final Manufacture MANUFACTURE_01 = getManufacture(1L, "ManufactureName-01");
    public static final Manufacture MANUFACTURE_02 = getManufacture(2L, "ManufactureName-02");
    public static final Manufacture MANUFACTURE_03 = getManufacture(3L, "ManufactureName-03");


    public static final ManufactureDto DTO_01 = getManufactureDto(1L, "Name-01");
    public static final ManufactureDto DTO_02 = getManufactureDto(2L, "Name-02");
    public static final ManufactureDto DTO_03 = getManufactureDto(3L, "Name-03");


    public static Manufacture getManufacture(Long id, String name) {
        Manufacture manufacture = new Manufacture();
        manufacture.setId(id);
        manufacture.setManufactureName(name);
        manufacture.setCode("code-" + id);
        manufacture.setDescription("Description");
        return manufacture;
    }

    public static ManufactureDto getManufactureDto(Long id, String name) {
        ManufactureDto manufacture = new ManufactureDto();
        manufacture.setId(id);
        manufacture.setManufactureName(name);
        manufacture.setCode("code-" + id);
        manufacture.setDescription("Description");
        return manufacture;
    }    

}
