package com.example.jpaspringboot.repository;

import com.example.jpaspringboot.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario , Integer > {

    //Query Metod
    List<Usuario> findByNome(String nome);

    List<Usuario> findByLogin(String login);

    List<Usuario> findByEmail(String email);

    //Query Override
    @Query("select u FROM Usuario u WHERE " +
            "u.nome LIKE %:valor% OR u.email LIKE %:valor% OR u.login LIKE %:valor% OR u.cpf LIKE %:valor% ")
    List<Usuario> findByAny(@Param("valor") String valor);
}
