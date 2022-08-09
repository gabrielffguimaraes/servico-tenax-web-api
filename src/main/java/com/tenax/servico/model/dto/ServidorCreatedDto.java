package com.tenax.servico.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorCreatedDto {
    Long id;
    String nome;
    String descricao;
    SetorCreatedDto setor;
}
