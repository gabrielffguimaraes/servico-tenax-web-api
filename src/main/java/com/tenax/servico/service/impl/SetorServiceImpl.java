package com.tenax.servico.service.impl;

import com.tenax.servico.model.entity.Setor;
import com.tenax.servico.repository.SetorRepository;
import com.tenax.servico.service.SetorService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SetorServiceImpl implements SetorService {
    private final SetorRepository setorRepository;

    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public List<Setor> findAll() {
        return setorRepository.findAll();
    }

    @Override
    public Setor save(Setor setor) {
        return setorRepository.save(setor);
    }

    public void deleteById(Long id) {
        setorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Setor inexistente"));

        setorRepository.deleteById(id);
    }
}
