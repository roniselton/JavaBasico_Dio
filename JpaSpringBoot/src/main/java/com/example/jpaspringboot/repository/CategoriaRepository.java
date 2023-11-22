package com.example.jpaspringboot.repository;

import com.example.jpaspringboot.model.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "categoria" , path = "categoria")
public interface CategoriaRepository extends CrudRepository<Categoria , Integer> {

    List<Categoria> findCategoriaByDescricaoStartsWithIgnoreCaseOrderByDescricao(String descricao);

    List<Categoria> findByDescricaoContainsIgnoreCase(String descricao);

    List<Categoria> findCategoriaByDescricaoLikeOrderByDescricao(String desc);

}
