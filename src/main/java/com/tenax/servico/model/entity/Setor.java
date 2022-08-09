package com.tenax.servico.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "setor")
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,unique = true)
    String descricao;

    @Column(nullable = false)
    String uf;

    @JsonIgnoreProperties("setor")
    @OneToMany(mappedBy = "setor", fetch = FetchType.EAGER)
    List<Servidor> servidor;
}
