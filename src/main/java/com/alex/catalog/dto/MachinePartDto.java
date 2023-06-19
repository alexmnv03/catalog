package com.alex.catalog.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alex.catalog.common.AbstractId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MachinePartDto extends AbstractId<Long> {
 
    @NotBlank(message = "The \"nameParts\" attribute must not be empty")
    private String nameParts;

    @NotBlank(message = "The \"code\" attribute must not be null")
    private String code;

    @NotBlank(message = "The \"version\" attribute must not be null")
    private String version;

    @NotNull(message = "The \"oemOrAnalog\" attribute must not be null")
    private Boolean oemOrAnalog;

    @NotNull(message = "The \"year\" attribute must not be null")
    private Integer year;

    private String imageFileName;

    @NotNull(message = "The \"manufacture\" attribute must not be null")
    private String manufacture;

    @NotNull(message = "The \"price\" attribute must not be null")
    private Double price;

}
