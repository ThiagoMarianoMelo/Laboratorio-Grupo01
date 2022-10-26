package com.backend.backend.models.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.entities.Vantagem;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, UUID> {
    @Modifying
    @Query("update Vantagem v set v.nome=?1, v.descricao=?2, v.custo=?3 where v.id=?4")
    void editarVantagem(String nome, String descricao, double custo, UUID id);
}
