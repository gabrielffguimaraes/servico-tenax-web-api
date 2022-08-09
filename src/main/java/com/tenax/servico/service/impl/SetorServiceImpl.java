package com.tenax.servico.service.impl;

import com.tenax.servico.helper.MapHelper;
import com.tenax.servico.model.dto.SetorCreateDto;
import com.tenax.servico.model.dto.SetorCreatedDto;
import com.tenax.servico.model.entity.Setor;
import com.tenax.servico.repository.ServidorRepository;
import com.tenax.servico.repository.SetorRepository;
import com.tenax.servico.service.SetorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SetorServiceImpl implements SetorService {
    private final SetorRepository setorRepository;

    private final ServidorRepository servidorRepository;
    private final ModelMapper modelMapper;

    private final MapHelper mapHelper;

    public SetorServiceImpl(SetorRepository setorRepository,
                            ServidorRepository servidorRepository,
                            ModelMapper modelMapper,
                            MapHelper mapHelper) {
        this.setorRepository = setorRepository;
        this.servidorRepository = servidorRepository;
        this.modelMapper = modelMapper;
        this.mapHelper = mapHelper;
    }

    public List<Setor> findAll() {
        return setorRepository.findAll();
    }

    @Override
    public SetorCreatedDto save(SetorCreateDto setorCreateDto) {
        long setorJaExiste = setorRepository.verificarDescricaoValida(setorCreateDto.getDescricao());
        if(setorJaExiste > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Setor já existe");
        }
        Setor s = modelMapper.map(setorCreateDto,Setor.class);
        return modelMapper.map(setorRepository.save(s),SetorCreatedDto.class);
    }

    public void deleteById(Long id) {
        Setor s  = setorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Setor inexistente"));

        if(!s.getServidor().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não foi possivel deletar este setor pois existem servidores vinculados ao mesmo .");
        }

        setorRepository.deleteById(id);
    }

    public Setor update(Long id, SetorCreateDto setorCreateDto) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Setor inexistente"));

        long setorJaExiste = setorRepository.verificarDescricaoValida(id,setorCreateDto.getDescricao());
        if(setorJaExiste > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Setor já existe");
        }

        setor.setDescricao(setorCreateDto.getDescricao());
        setor.setUf(setorCreateDto.getUf());

        return setorRepository.save(setor);
    }

    @Override
    public Setor findById(Long id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Setor inexistente"));
    }
}
