package com.alex.catalog.integration.service;

import com.alex.catalog.repository.ManufactureRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.alex.catalog.service.ManufactureService;

import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_01;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_02;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_03;

@Slf4j
// @ExtendWith(SpringExtension.class)
@SpringBootTest
@Rollback(true)
public class ManufactureServiceIntTest {
  
    @Mock
    private ManufactureRepository manufactureRepository;

    @Autowired
    private ManufactureService manufactureService;

    @Test
    @Transactional
    public void saveManufacture_shouldOK() {
        var result = manufactureService.create(MANUFACTURE_01);
        assertThat(result, notNullValue());
        assertThat(result.getManufactureName(), is("ManufactureName-01"));
        assertThat(result.getCode(), is("code-1"));
        assertThat(result.getDescription(), is("Description"));
    }

    @Test
    @Transactional
    public void updateManufacture_shouldOK() {
        var result = manufactureService.create(MANUFACTURE_02);
        var manufacture = manufactureService.get(result.getId());

        manufacture.setManufactureName("Manufacture");

        var resultUpdate = manufactureService.create(manufacture);

        assertThat(resultUpdate, notNullValue());
        assertThat(resultUpdate.getId(), is(result.getId()));
        assertThat(resultUpdate.getManufactureName(), is("Manufacture"));
        assertThat(resultUpdate.getCode(), is("code-2"));
        assertThat(resultUpdate.getDescription(), is("Description"));
    }

    @Test
    // @Sql({"/getManufactures_shouldOk.sql"})    
    @Transactional
    public void getManufactures_shouldOk() {
        manufactureService.create(MANUFACTURE_01);
        manufactureService.create(MANUFACTURE_02);
        manufactureService.create(MANUFACTURE_03);
        var result = manufactureService.getAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), is(3));
    }

    @Test
    @Transactional
    public void getManufacture_shouldOk() {
        var resultSaved = manufactureService.create(MANUFACTURE_02);
        var result = manufactureService.get(resultSaved.getId());
        // var result = manufactureRepository.findAll();

        assertThat(result, notNullValue());
        assertThat(result.getManufactureName(), is("ManufactureName-02"));
        assertThat(result.getCode(), is("code-2"));
        assertThat(result.getDescription(), is("Description"));
    }

    @Test
    @Transactional
    public void delete_shouldOk() {
        manufactureService.create(MANUFACTURE_01);
        var resultSaved = manufactureService.create(MANUFACTURE_02);
        manufactureService.create(MANUFACTURE_03);
        manufactureService.delete(resultSaved.getId());

        var result = manufactureService.getAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), is(2));
    }    

}
