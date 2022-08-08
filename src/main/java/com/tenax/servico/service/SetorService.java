package com.tenax.servico.service;


import com.tenax.servico.model.entity.Setor;

import java.util.List;

public interface SetorService {
    List<Setor> findAll();

    Setor save(Setor setor);

    void deleteById(Long id);
}
