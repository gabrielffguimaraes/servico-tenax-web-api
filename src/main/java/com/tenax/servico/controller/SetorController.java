package com.tenax.servico.controller;


import com.tenax.servico.model.dto.SetorCreateDto;
import com.tenax.servico.model.dto.SetorCreatedDto;
import com.tenax.servico.model.entity.Setor;
import com.tenax.servico.service.impl.SetorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/setor")
@Tag(name = "Setores", description = "manter setores endpoits api")
public class SetorController {
    private final SetorServiceImpl setorService;

    public SetorController(SetorServiceImpl setorService) {
        this.setorService = setorService;
    }

    @GetMapping
    @Operation(summary = "Listar todos")
    public ResponseEntity<List<Setor>> findAll() {
        return ResponseEntity.ok(setorService.findAll());
    }


    @Operation(summary = "Listar por id")
    @GetMapping("{id}")
    public ResponseEntity<Setor> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(setorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Criar novo setor")
    public ResponseEntity<SetorCreatedDto> create(@RequestBody @Valid SetorCreateDto setor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(setorService.save(setor));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar setor")
    public void delete(@PathVariable Long id) {
        setorService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar setor")
    public ResponseEntity<Setor> update(@RequestBody @Valid SetorCreateDto setor,@PathVariable Long id) {
        return ResponseEntity.ok(setorService.update(id,setor));
    }
}
