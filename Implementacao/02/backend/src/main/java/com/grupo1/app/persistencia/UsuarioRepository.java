package com.grupo1.app.persistencia;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo1.app.entidades.bussiness.usuarios.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    @Query("select u from Usuario u where u.email = ?1 and u.senha = ?2")
    Usuario findUserByEmailAndSenha(String email, String senha);
}
