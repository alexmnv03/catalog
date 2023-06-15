package com.alex.catalog.dto;

import javax.validation.constraints.NotBlank;

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
public class ManufactureDto extends AbstractId<Long> {

    @NotBlank(message = "The \"name\" attribute must not be empty")
    private String manufactureName;

    @NotBlank(message = "The \"code\" attribute must not be empty")
    private String code;

    private String description;

}
