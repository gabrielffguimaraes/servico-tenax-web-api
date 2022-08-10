package com.tenax.servico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorCreateDto {
    @NotBlank(message = "Nome obrigatorio")
    String nome;
    @NotBlank(message = "Descricao obrigatoria")
    String descricao;
    @NotNull(message = "Setor obrigatorio")
    Long setorId;
}

