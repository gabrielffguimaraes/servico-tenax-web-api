package com.tenax.servico.controller;

import com.tenax.servico.model.dto.ServidorCreateDto;
import com.tenax.servico.model.dto.ServidorCreatedDto;
import com.tenax.servico.model.entity.Servidor;
import com.tenax.servico.service.impl.ServidorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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
    public ResponseEntity<Page<Servidor>> findAll(
            @RequestParam(name = "nome",required = false,defaultValue = "") String nome,
            @RequestParam(name = "pagina",required = false,defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanho",required = false,defaultValue = "10") Integer tamanho
    ) {
        Pageable p = PageRequest.of(pagina,tamanho);
        Page<Servidor> servidores = servidorService.findAll(p,nome);
        return ResponseEntity.ok(servidores);
    }

    @Operation(summary = "Listar por id")
    @GetMapping("{id}")
    public ResponseEntity<Servidor> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(servidorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Criar servidor")
    public ResponseEntity<ServidorCreatedDto> create(@RequestBody @Valid ServidorCreateDto servidor) {
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
    public Servidor update(@RequestBody @Valid ServidorCreateDto servidor,@PathVariable Long id) {
        return servidorService.update(id,servidor);
    }
}
