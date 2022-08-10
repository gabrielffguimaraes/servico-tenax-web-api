package com.tenax.servico.service;


import com.tenax.servico.model.dto.SetorCreateDto;
import com.tenax.servico.model.dto.SetorCreatedDto;
import com.tenax.servico.model.entity.Setor;

import java.util.List;

public interface SetorService {
    List<Setor> findAll(String descricao);

    SetorCreatedDto save(SetorCreateDto setor);

    void deleteById(Long id);

    Setor update(Long id, SetorCreateDto setorDto);

    Setor findById(Long id);
}
