package com.tenax.servico.service;

import com.tenax.servico.model.dto.ServidorCreateDto;
import com.tenax.servico.model.dto.ServidorCreatedDto;
import com.tenax.servico.model.entity.Servidor;
import com.tenax.servico.model.entity.Setor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServidorService {
    Page<Servidor> findAll(Pageable p, String nome);

    ServidorCreatedDto save(ServidorCreateDto servidor);

    void deleteById(Long id);

    Servidor update(Long id, ServidorCreateDto ServidorCreateDto);

    Servidor findById(Long id);
}
