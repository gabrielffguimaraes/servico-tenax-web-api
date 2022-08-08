package com.tenax.servico.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "servidor")
public class Servidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,unique = true)
    String nome;

    @Column(nullable = false,unique = true)
    String descricao;

    @ManyToOne(optional = false)
    Setor setor;
}

