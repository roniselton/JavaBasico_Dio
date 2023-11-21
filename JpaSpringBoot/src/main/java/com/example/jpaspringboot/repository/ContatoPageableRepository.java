package com.example.jpaspringboot.repository;

import com.example.jpaspringboot.model.Contato;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ContatoPageableRepository extends ListPagingAndSortingRepository<Contato, Integer> {



}
