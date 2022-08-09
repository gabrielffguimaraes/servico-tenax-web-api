package com.tenax.servico.service;

import com.tenax.servico.model.dto.ServidorCreateDto;
import com.tenax.servico.model.dto.ServidorCreatedDto;
import com.tenax.servico.model.entity.Servidor;

import java.util.List;

public interface ServidorService {
    List<Servidor> findAll();

    ServidorCreatedDto save(ServidorCreateDto servidor);

    void deleteById(Long id);

    Servidor update(Long id, ServidorCreateDto ServidorCreateDto);

}
