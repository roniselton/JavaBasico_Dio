package com.example.jpaspringboot.repository;

import com.example.jpaspringboot.model.Usuario;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface UsuarioOutroRepository extends ListPagingAndSortingRepository<Usuario , Integer> {


}
