package com.example.jpaspringboot.repository;

import com.example.jpaspringboot.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface UsuarioOutroRepository extends ListPagingAndSortingRepository<Usuario , Integer> {


}
