package com.tenax.servico.repository;

import com.tenax.servico.model.entity.Servidor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor,Long> {
    @Query("select count(s) from Servidor s where upper(s.nome) = upper(:nome) and s.id != :id")
    Long verificarNomeValido(@Param("id") Long id, @Param("nome") String nome);

    @Query("select count(s) from Servidor s where upper(s.nome) = upper(:nome)")
    Long verificarNomeValido(@Param("nome") String nome);

    @Query("select s from Servidor s where upper(s.nome) like upper(:nome) and :nome != ''")
    Page<Servidor> findAll(Pageable var1, @Param("nome") String nome);
}
