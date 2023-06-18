package com.alex.catalog.integration.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.alex.catalog.dto.ManufactureDto;
import com.alex.catalog.entity.Manufacture;
import com.alex.catalog.service.ManufactureService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static com.alex.catalog.util.ManufactureUtil.DTO_01;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_01;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_02;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_03;

@Slf4j
// @ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManufactureControllerIntTest {
    
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ManufactureService manufactureService;

    @Autowired
    protected ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    @Transactional
    public void create_shouldOk() throws Exception {
        var result = mockMvc.perform(post("/manufacture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(DTO_01)))
                .andExpect(status().isOk());

        var addedManufacture = objectMapper.readValue(result.andReturn()
            .getResponse().getContentAsString(), 
            ManufactureDto.class);

        assertThat(addedManufacture, notNullValue());
        assertThat(addedManufacture.getManufactureName(), is("Name-01"));
        assertThat(addedManufacture.getCode(), is("code-1"));
        assertThat(addedManufacture.getDescription(), is("Description"));
        assertThat(addedManufacture.getId(), notNullValue());
    }

    // @Sql({"/getManufactures_shouldOk.sql"})
    // @Rollback(false)
    @Test
    @Transactional
    public void getAll_shouldOk() throws Exception {
        manufactureService.create(MANUFACTURE_01);
        manufactureService.create(MANUFACTURE_02);
        manufactureService.create(MANUFACTURE_03);
        var result = mockMvc.perform(get("/manufacture")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        List<ManufactureDto> manufactures = objectMapper.readValue(result.andReturn()
            .getResponse().getContentAsString(),
                new TypeReference<List<ManufactureDto>>() {
                });

        assertThat(manufactures, notNullValue());
        assertThat(manufactures.size(), is(3));
    }

    @Test
    @Transactional
    public void getOne_shouldOk() throws Exception {
        log.info("-------------------  one   ----------------------------");
        Manufacture manufactureSaved = manufactureService.create(MANUFACTURE_01);
        Long idSaved = manufactureSaved.getId();
        log.info("id = = " + manufactureSaved.getId());
        var result = mockMvc.perform(get("/manufacture/" + idSaved)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        var manufacture = objectMapper.readValue(result.andReturn()
            .getResponse().getContentAsString(), 
            ManufactureDto.class);

        assertThat(manufacture, notNullValue());
        assertThat(manufacture.getId(), is(idSaved));
    }

    @Test
    @Transactional
    public void delete_shouldOk() throws Exception {
        manufactureService.create(MANUFACTURE_01);
        manufactureService.create(MANUFACTURE_02);
        manufactureService.create(MANUFACTURE_03);
        var result = mockMvc.perform(get("/manufacture")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        List<ManufactureDto> manufactures = objectMapper.readValue(result.andReturn()
            .getResponse().getContentAsString(),
                new TypeReference<List<ManufactureDto>>() {
                });

        assertThat(manufactures, notNullValue());
        assertThat(manufactures.size(), is(3));

        mockMvc.perform(delete("/manufacture/delete/2")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        result = mockMvc.perform(get("/manufacture")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        manufactures = objectMapper.readValue(result.andReturn()
            .getResponse().getContentAsString(),
                new TypeReference<List<ManufactureDto>>() {
                });

        assertThat(manufactures, notNullValue());
        assertThat(manufactures.size(), is(2));

    }
        
}
