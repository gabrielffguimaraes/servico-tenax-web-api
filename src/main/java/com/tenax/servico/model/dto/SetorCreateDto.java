package com.tenax.servico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetorCreateDto {
    @NotBlank(message = "Descricao obrigatória")
    @Length(min = 2,max = 255,message = "Descrição Deve conter no minimo 2 caracteres e no máximo 255 .")
    String descricao;
    @NotBlank(message = "UF obrigatório")
    @Length(min = 2,max = 2,message = "UF Deve conter 2 caracteres")
    String uf;
}
