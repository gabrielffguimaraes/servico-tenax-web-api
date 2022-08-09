package com.tenax.servico.service.impl;

import com.tenax.servico.helper.MapHelper;
import com.tenax.servico.model.dto.ServidorCreateDto;
import com.tenax.servico.model.dto.ServidorCreatedDto;
import com.tenax.servico.model.entity.Servidor;
import com.tenax.servico.model.entity.Setor;
import com.tenax.servico.repository.ServidorRepository;
import com.tenax.servico.repository.SetorRepository;
import com.tenax.servico.service.ServidorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServidorServiceImpl implements ServidorService {

    private final MapHelper mapHelper;
    private final ModelMapper modelMapper;
    private final ServidorRepository servidorRepository;

    private final SetorRepository setorRepository;

    public ServidorServiceImpl(MapHelper mapHelper, ModelMapper modelMapper, ServidorRepository servidorRepository, SetorRepository setorRepository) {
        this.mapHelper = mapHelper;
        this.modelMapper = modelMapper;
        this.servidorRepository = servidorRepository;
        this.setorRepository = setorRepository;
    }

    public List<Servidor> findAll() {
        return servidorRepository.findAll();
    }

    @Override
    public ServidorCreatedDto save(ServidorCreateDto sevidorCreateDto) {
        Setor setor = setorRepository.findById(sevidorCreateDto.getSetorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Setor inexistente"));

        Servidor s = modelMapper.map(sevidorCreateDto,Servidor.class);
        return modelMapper.map(servidorRepository.save(s),ServidorCreatedDto.class);
    }

    public void deleteById(Long id) {
        servidorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Servidor inexistente"));

        servidorRepository.deleteById(id);
    }

    public Servidor update(Long id, ServidorCreateDto ServidorCreateDto) {
        Setor setor = setorRepository.findById(ServidorCreateDto.getSetorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Setor inexistente"));
        Servidor Servidor = servidorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Servidor inexistente"));

        Servidor.setNome(ServidorCreateDto.getNome());
        Servidor.setDescricao(ServidorCreateDto.getDescricao());
        Servidor.setSetor(setor);
        return servidorRepository.save(Servidor);
    }
}
