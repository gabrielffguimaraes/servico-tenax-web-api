package com.tenax.servico.controller;


import com.tenax.servico.model.dto.SetorCreateDto;
import com.tenax.servico.model.dto.SetorCreatedDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SetorControllerTest extends AbstractContainerBase {

    String URL_SERVER = "/api/v1/setor";

    private ObjectMapper objectMapper = new ObjectMapper();
    @LocalServerPort
    private int randomPort;


    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }
    @Test
    void whenCreateThenCheckResult() {
        SetorCreateDto setorCreateDto = new SetorCreateDto();
        setorCreateDto.setDescricao("Setor de laticinios");
        setorCreateDto.setUf("RJ");

        given()
                .contentType(ContentType.JSON)
                .body(setorCreateDto)
                .when()
                .post(URL_SERVER)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("descricao", Matchers.equalTo("Setor de laticinios"))
                .body("id",Matchers.notNullValue())
                .extract().response().body().prettyPrint();
    }

    @Test
    public void whenFindAllThenCheckResult() {
        SetorCreateDto setorCreateDto = new SetorCreateDto();
        setorCreateDto.setDescricao("Setor de modas");
        setorCreateDto.setUf("BH");

        given()
                .contentType(ContentType.JSON)
                .body(setorCreateDto)
                .when()
                .post(URL_SERVER);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL_SERVER)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(true, response.asString().contains("BH"));
    }

    @Test
    public void whenDeleteThenCheckResult() throws IOException {
        SetorCreateDto setorCreateDto = new SetorCreateDto();
        setorCreateDto.setDescricao("Setor de Saúde publica");
        setorCreateDto.setUf("RJ");

        ResponseBody responseBody = given()
                .contentType(ContentType.JSON)
                .body(setorCreateDto)
                .when()
                .post(URL_SERVER);
        SetorCreatedDto created = objectMapper.readValue(responseBody.asString(),SetorCreatedDto.class);

        Assertions.assertEquals("RJ", created.getUf());

        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id",created.getId())
                .when()
                .delete(URL_SERVER+"/{id}")
                .then()
                .extract().response();

        Assertions.assertEquals(204, response.statusCode());
    }

    @Test
    void whenUpdateThenCheckResult() throws IOException, JSONException {
        SetorCreateDto setorCreateDto = new SetorCreateDto();
        setorCreateDto.setDescricao("Setor de Contabilidade");
        setorCreateDto.setUf("RJ");

        Response responseBody = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(setorCreateDto)
                .when()
                .post(URL_SERVER).andReturn();
        SetorCreatedDto created = objectMapper.readValue(responseBody.asString(),SetorCreatedDto.class);

        Assertions.assertEquals(201, responseBody.statusCode());
        Assertions.assertEquals(created.getDescricao(), "Setor de Contabilidade");

        JSONObject item = new JSONObject();
        item.put("descricao", "Setor de Atendimento");
        item.put("uf", "RJ");

        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .pathParam("id",created.getId())
                .and()
                .body(item.toString())
                .when()
                .put(URL_SERVER+"/{id}")
                .andReturn();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(true,response.asString().contains("Setor de Atendimento"));
    }


}
