package com.alex.catalog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alex.catalog.common.AbstractId;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class MachineDto extends AbstractId<Long> {
    
    @NotBlank(message = "The \"code\" attribute must not be empty")
    private String code;

    @NotBlank(message = "The \"model\" attribute must not be null")
    private String model;

    @NotNull(message = "The \"year\" attribute must not be null")
    private Integer year;

    private String imageFileName;

    @NotNull(message = "The \"manufacture\" attribute must not be null")
    private String manufacture;

    private String codeMachinePart;

}
