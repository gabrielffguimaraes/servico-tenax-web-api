package com.tenax.servico.repository;

import com.tenax.servico.model.entity.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor,Long> {
    List<Servidor> findAll();
}
