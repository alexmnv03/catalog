package com.alex.catalog.integration.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.alex.catalog.controller.ManufactureController;
import com.alex.catalog.dto.ManufactureDto;
import com.alex.catalog.facade.ManufactureFacade;
import com.alex.catalog.service.ManufactureService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import static com.alex.catalog.util.ManufactureUtil.DTO_01;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_01;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_02;
import static com.alex.catalog.util.ManufactureUtil.MANUFACTURE_03;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManufactureControllerAssuredIntTest {

    @LocalServerPort
    private int port;

    @MockBean
    private ManufactureFacade manufactureFacade;

    @Autowired
    private ManufactureService manufactureService;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    public void setup() {
        standaloneSetup(new ManufactureController(manufactureFacade));
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void whenCreate_thenStatus200() {

        when(manufactureFacade.create(DTO_01)).thenReturn(DTO_01);

        given().
                contentType(ContentType.JSON).
                body(DTO_01).
                when().
                post("/manufacture").
                then().
                statusCode(200).
                and().
                extract().body().
                as(ManufactureDto.class).
                equals(DTO_01);
    }

    @Test
    public void whenUpdate_thenStatus2000() {

        when(manufactureFacade.create(DTO_01)).thenReturn(DTO_01);

        given().
                contentType(ContentType.JSON).
                body(DTO_01).
                when().
                put("/manufacture/update").
                then().
                statusCode(200).
                body("id", equalTo(1)).
                body("manufactureName", equalTo("Name-01"));
    }

    @Test
    @Transactional
    public void getAll_shouldOk() throws Exception {
        manufactureService.create(MANUFACTURE_01);
        manufactureService.create(MANUFACTURE_02);
        manufactureService.create(MANUFACTURE_03);

        given().
                contentType(ContentType.JSON).
                when().
                get("/manufacture").
                then().
                statusCode(200).
                and().
                extract().body().
                as(ManufactureDto[].class);
    }

    @Test
    @Transactional
    public void getOne_shouldOk() throws Exception {

        when(manufactureFacade.getOne(1L)).thenReturn(DTO_01);

        given().
                contentType(ContentType.JSON).
                param("id", "1").
                when().
                get("/manufacture").
                then().
                statusCode(200);
    }

}
