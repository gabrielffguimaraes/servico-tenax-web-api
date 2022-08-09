package com.tenax.servico.controller;

import com.tenax.servico.model.dto.ServidorCreateDto;
import com.tenax.servico.model.dto.ServidorCreatedDto;
import com.tenax.servico.model.entity.Servidor;
import com.tenax.servico.service.impl.ServidorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/servidor")
@Tag(name = "Servidores", description = "manter servidores endpoits api")
public class ServidorController {
    private final ServidorServiceImpl servidorService;

    public ServidorController(ServidorServiceImpl servidorService) {
        this.servidorService = servidorService;
    }

    @GetMapping
    @Operation(summary = "Listar todos")
    public ResponseEntity<List<Servidor>> findAll() {
        List<Servidor> servidores = servidorService.findAll();
        return ResponseEntity.ok(servidores);
    }

    @PostMapping
    @Operation(summary = "Criar servidor")
    public ResponseEntity<ServidorCreatedDto> create(@RequestBody ServidorCreateDto servidor) {
        ServidorCreatedDto servidorCreated = servidorService.save(servidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(servidorCreated);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar servidor")
    public void delete(@PathVariable Long id) {
        servidorService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar servidor")
    public Servidor update(@RequestBody ServidorCreateDto servidor,@PathVariable Long id) {
        return servidorService.update(id,servidor);
    }
}
