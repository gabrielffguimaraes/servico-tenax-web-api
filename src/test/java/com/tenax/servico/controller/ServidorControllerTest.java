package com.tenax.servico.controller;

import com.tenax.servico.model.dto.ServidorCreateDto;
import com.tenax.servico.model.dto.ServidorCreatedDto;
import com.tenax.servico.model.dto.SetorCreateDto;
import com.tenax.servico.model.entity.Setor;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
public class ServidorControllerTest extends AbstractContainerBase {

    String URL_SERVER = "/api/v1/servidor";
    String URL_SERVER_SETOR = "/api/v1/setor";
    private ObjectMapper objectMapper = new ObjectMapper();
    @LocalServerPort
    private int randomPort;


    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }
    @Test
    void whenCreateThenCheckResult() throws IOException {
        SetorCreateDto setorCreateDto = new SetorCreateDto();
        setorCreateDto.setDescricao("Setor 1");
        setorCreateDto.setUf("RJ");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(setorCreateDto)
                .when()
                .post(URL_SERVER_SETOR).andReturn();
        Setor setor = objectMapper.readValue(response.asString(),Setor.class);

        ServidorCreateDto servidorCreateDto = new ServidorCreateDto();

        servidorCreateDto.setNome("Ricardo da silva Sants");
        servidorCreateDto.setDescricao("Responsavel por cuidar do controle de estoque");
        servidorCreateDto.setSetorId(setor.getId());
        given()
                .contentType(ContentType.JSON)
                .body(servidorCreateDto)
                .when()
                .post(URL_SERVER)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("nome", Matchers.equalTo("Ricardo da silva Sants"))
                .body("id",Matchers.notNullValue())
                .extract().response().body().prettyPrint();
    }

    @Test
    public void whenFindAllThenCheckResult() throws IOException {
        SetorCreateDto setorCreateDto = new SetorCreateDto();
        setorCreateDto.setDescricao("Setor 2");
        setorCreateDto.setUf("RJ");
        Response responseSetor = given()
                .contentType(ContentType.JSON)
                .body(setorCreateDto)
                .when()
                .post(URL_SERVER_SETOR).andReturn();

        Setor setor = objectMapper.readValue(responseSetor.asString(),Setor.class);

        ServidorCreateDto servidorCreateDto = new ServidorCreateDto();

        servidorCreateDto.setNome("Carlos de Oliveira");
        servidorCreateDto.setDescricao("Responsavel pela contabilidade");
        servidorCreateDto.setSetorId(setor.getId());
        given()
                .contentType(ContentType.JSON)
                .body(servidorCreateDto)
                .when()
                .post(URL_SERVER)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("nome", Matchers.equalTo("Carlos de Oliveira"))
                .body("id",Matchers.notNullValue())
                .extract().response().body().prettyPrint();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL_SERVER)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(true, response.asString().contains("Responsavel pela contabilidade"));
    }

    @Test
    public void whenDeleteThenCheckResult() throws IOException {
        SetorCreateDto setorCreateDto = new SetorCreateDto();
        setorCreateDto.setDescricao("Setor 3");
        setorCreateDto.setUf("BH");
        Response responseSetor = given()
                .contentType(ContentType.JSON)
                .body(setorCreateDto)
                .when()
                .post(URL_SERVER_SETOR).andReturn();

        Setor setor = objectMapper.readValue(responseSetor.asString(),Setor.class);

        ServidorCreateDto servidorCreateDto = new ServidorCreateDto();

        servidorCreateDto.setNome("Artemises da silva");
        servidorCreateDto.setDescricao("Responsavel pela cantina");
        servidorCreateDto.setSetorId(setor.getId());
        Response responseServidor = given()
                .contentType(ContentType.JSON)
                .body(servidorCreateDto)
                .when()
                .post(URL_SERVER).andReturn();

        ServidorCreatedDto createdDto = objectMapper.readValue(responseServidor.asString(),ServidorCreatedDto.class);

        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id",createdDto.getId())
                .when()
                .delete(URL_SERVER+"/{id}")
                .then()
                .extract().response();

        Assertions.assertEquals(204, response.statusCode());
    }

    @Test
    void whenUpdateThenCheckResult() throws IOException, JSONException {
        SetorCreateDto setorCreateDto = new SetorCreateDto();
        setorCreateDto.setDescricao("Setor 4");
        setorCreateDto.setUf("MT");
        Response responseSetor = given()
                .contentType(ContentType.JSON)
                .body(setorCreateDto)
                .when()
                .post(URL_SERVER_SETOR).andReturn();

        Setor setor = objectMapper.readValue(responseSetor.asString(),Setor.class);

        ServidorCreateDto servidorCreateDto = new ServidorCreateDto();

        servidorCreateDto.setNome("Aloisio santo amargo");
        servidorCreateDto.setDescricao("Responsavel pela cantina");
        servidorCreateDto.setSetorId(setor.getId());
        Response responseServidor = given()
                .contentType(ContentType.JSON)
                .body(servidorCreateDto)
                .when()
                .post(URL_SERVER).andReturn();

        ServidorCreatedDto createdDto = objectMapper.readValue(responseServidor.asString(),ServidorCreatedDto.class);

        JSONObject item = new JSONObject();
        item.put("nome", "Vini de lisboa");
        item.put("descricao", "Responsavel pela cantina");
        item.put("setorId", setor.getId());

        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .pathParam("id",createdDto.getId())
                .and()
                .body(item.toString())
                .when()
                .put(URL_SERVER+"/{id}")
                .andReturn();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(true,response.asString().contains("Vini de lisboa"));
    }


}
