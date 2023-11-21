package com.example.jpaspringboot.dto;


import com.example.jpaspringboot.model.Contato;

import java.time.LocalDate;

public record ContatoRecord(
        Integer id,
        String nome,
        String sobrenome,
        String email,
        String celular,
        LocalDate dataNascimento ) {

    public static ContatoRecord convertToRecord(Contato contato){
        return new ContatoRecord(
                contato.getId(),
                contato.getNome(),
                contato.getSobrenome(),
                contato.getEmail(),
                contato.getCelular(),
                contato.getDataNascimento());
    }

    public Contato convertToEntity(){
        return Contato.builder()
                .id( this.id() )
                .nome( this.nome() )
                .sobrenome( this.sobrenome() )
                .email( this.email() )
                .celular( this.celular() )
                .dataNascimento( this.dataNascimento() )
                .build();
    }
}
