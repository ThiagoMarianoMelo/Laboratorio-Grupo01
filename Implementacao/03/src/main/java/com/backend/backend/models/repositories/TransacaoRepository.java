package com.backend.backend.models.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.entities.transacao.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
    @Query("select t from Transacao t where t.usuario = ?1")
    Optional<List<Transacao>> getTransacoesByUsuarioId(UUID usuarioId);
}
