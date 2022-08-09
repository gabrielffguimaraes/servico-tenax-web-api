package com.tenax.servico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorCreateDto {
    String nome;
    String descricao;
    Long setorId;
}

