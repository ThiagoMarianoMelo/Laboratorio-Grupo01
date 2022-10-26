package com.backend.backend.models.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.backend.models.entities.usuarios.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    @Query("select u from Usuario u where u.email = ?1 and u.senha = ?2")
    Usuario findUserByEmailAndSenha(String email, String senha);

    @Modifying
    @Query("update Usuario u set u.saldo=?1 where u.id=?2 ")
    void atualizarSaldo(double valor, UUID id);
}
