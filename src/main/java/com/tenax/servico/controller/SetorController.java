package com.tenax.servico.controller;


import com.tenax.servico.model.entity.Setor;
import com.tenax.servico.service.impl.SetorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/setor")
public class SetorController {
    private final SetorServiceImpl setorService;

    public SetorController(SetorServiceImpl setorService) {
        this.setorService = setorService;
    }

    @GetMapping
    public ResponseEntity<List<Setor>> findAll() {
        List<Setor> setores = setorService.findAll();
        return ResponseEntity.ok(setores);
    }

    @PostMapping
    public ResponseEntity<Setor> create(@RequestBody Setor setor) {
        Setor setorCreated = setorService.save(setor);
        return ResponseEntity.status(HttpStatus.CREATED).body(setorCreated);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        setorService.deleteById(id);
    }
}
