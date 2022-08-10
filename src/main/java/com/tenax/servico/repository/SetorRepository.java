package com.tenax.servico.repository;

import com.tenax.servico.model.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SetorRepository extends JpaRepository<Setor,Long> {
    @Query("select count(s) from Setor s where upper(s.descricao) = upper(:descricao) and s.id != :id")
    Long verificarDescricaoValida(@Param("id") Long id,@Param("descricao") String descricao);

    @Query("select count(s) from Setor s where upper(s.descricao) = upper(:descricao)")
    Long verificarDescricaoValida(@Param("descricao") String descricao);

    @Query("select s from Setor s where upper(s.descricao) like upper(:descricao) and :descricao != ''")
    List<Setor> findAll(@Param("descricao") String descricao);
}
