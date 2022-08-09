package com.tenax.servico.helper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapHelper<T,E> {

    private final ModelMapper modelMapper;

    public MapHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<E> map(List<T> list, Class<E> c) {
        return list.stream().map(item -> modelMapper.map(item,c)).collect(Collectors.toList());
    }
}
