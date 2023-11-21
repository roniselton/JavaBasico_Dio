package com.example.jpaspringboot.service;

import com.example.jpaspringboot.dto.ContatoRecord;
import com.example.jpaspringboot.model.Contato;
import com.example.jpaspringboot.repository.ContatoPageableRepository;
import com.example.jpaspringboot.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    @Autowired
    private ContatoPageableRepository pageableRepository;

    public Optional<Contato> createContato(ContatoRecord record){
        Contato contato = record.convertToEntity();
        return Optional.of( repository.save( contato ) );
    }

    public Optional<Contato> updateContato(ContatoRecord record){
        Contato contato = record.convertToEntity();
        if( ! repository.existsById( contato.getId() ))
            throw new IllegalArgumentException();
        return Optional.of( repository.save( contato ) );
    }

    public Optional<Contato> excluir(Integer id){
        Optional<Contato> ret = repository.findById( id );
        if( ret.isPresent() ) {
            repository.deleteById(id);
        }
        return ret;
    }

    public List<Contato> listarTodos(){
        return repository.findAll();
    }

    public Page<Contato> listarPaginado(Pageable pageable){
        return pageableRepository.findAll( pageable );
    }

    public Optional<Contato> findById(Integer id){
        return repository.findById( id );
    }

}
