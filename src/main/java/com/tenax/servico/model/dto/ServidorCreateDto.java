package com.tenax.servico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorCreateDto {
    @NotBlank(message = "Nome obrigatorio")
    @Length(min = 2,max = 255,message = "Nome Deve conter no minimo 2 caracteres e no máximo 255 .")
    String nome;

    @NotBlank(message = "Descricao obrigatoria")
    @Length(min = 2,max = 255,message = "Descrição Deve conter no minimo 2 caracteres e no máximo 255 .")
    String descricao;

    @NotNull(message = "Setor obrigatorio")
    Long setorId;
}

