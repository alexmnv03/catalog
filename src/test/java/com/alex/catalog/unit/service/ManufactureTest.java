package com.alex.catalog.unit.service;


import com.alex.catalog.repository.ManufactureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alex.catalog.entity.Manufacture;
import com.alex.catalog.error.NotFoundException;
import com.alex.catalog.service.Impl.ManufactureServiceImpl;
import com.alex.catalog.util.ManufactureUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_01;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_02;

@ExtendWith(MockitoExtension.class)
public class ManufactureTest {

    @Mock
    ManufactureRepository manufactureRepository;

    @InjectMocks
    private ManufactureServiceImpl manufactureService;

    @Test
    public void saveManufacture_shouldOK() {
        when(manufactureRepository.save(any())).thenReturn(MANUFACTURE_01);
        var result = manufactureService.create(MANUFACTURE_01);

        assertThat(result, notNullValue());
        assertThat(result.getId(), is(1L));
        assertThat(result.getManufactureName(), is("ManufactureName-01"));
        assertThat(result.getCode(), is("code-1"));
        assertThat(result.getDescription(), is("Description"));
        verify(manufactureRepository).save(MANUFACTURE_01);
    }

    @Test
    public void updateManufacture_shouldOK() {
        Manufacture manufactureUpdate = ManufactureUtil.getManufacture(2L, "Manufacture");

        when(manufactureRepository.save(MANUFACTURE_02)).thenReturn(MANUFACTURE_02);
        when(manufactureRepository.save(manufactureUpdate)).thenReturn(manufactureUpdate);

        var result = manufactureService.create(MANUFACTURE_02);
        result.setManufactureName("Manufacture");
        var resultUpdate = manufactureService.create(result);

        assertThat(resultUpdate, notNullValue());
        assertThat(resultUpdate.getId(), is(2L));
        assertThat(resultUpdate.getManufactureName(), is("Manufacture"));
        assertThat(resultUpdate.getCode(), is("code-2"));
        assertThat(resultUpdate.getDescription(), is("Description"));
    }

    @Test
    public void getManufactures_shouldOk() {
        var listResponse = Arrays.asList(MANUFACTURE_01, MANUFACTURE_02);
        when(manufactureRepository.findAll()).thenReturn(listResponse);
        var result = manufactureService.getAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), is(2));
        verify(manufactureRepository).findAll();
    }

    @Test
    public void getManufactures_shouldEmptyList() {
        List<Manufacture> listResponse = Collections.emptyList();

        when(manufactureRepository.findAll()).thenReturn(listResponse);

        var result = manufactureService.getAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), is(0));
        verify(manufactureRepository).findAll();
    }    

    @Test
    public void getManufactureId_shouldOk() {
        when(manufactureRepository.findById(1L)).thenReturn(Optional.of(MANUFACTURE_01));

        var result = manufactureService.get(1L);

        assertThat(result, notNullValue());
        assertThat(result.getId(), is(1L));
        assertThat(result.getManufactureName(), is("ManufactureName-01"));
        assertThat(result.getCode(), is("code-1"));
        assertThat(result.getDescription(), is("Description"));
        verify(manufactureRepository).findById(1L);
    }    

    @Test
    void getManufactureId_shouldNotFoundException() {
        when(manufactureRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> manufactureService.get(1L));
        verify(manufactureRepository).findById(1L);
    }

    @Test
    public void deleteManufactureById_shouldOk() {
        manufactureService.delete(1L);
        verify(manufactureRepository).deleteById(1L);
    }

}
