package com.example.jpaspringboot.repository;

import com.example.jpaspringboot.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrudRepository extends CrudRepository<Usuario , Integer> {


}
