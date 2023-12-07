package com.example.jpaspringboot.dto;

import com.example.jpaspringboot.model.Contato;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO extends RepresentationModel<ContatoDTO> {

    private Integer id;

    @NotNull(message = "Nome é obrigatório.")
    @Length(min = 3, message = "O nome deve ter no mínimo 3 caracteres.")
    private String nome;

    private String sobrenome;

    @Email( message = "Email inválido.")
    private String email;

    private String celular;

    @Past(message = "Data de Nascimento inválida.")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR")
    private LocalDate dataNascimento;

    public static ContatoDTO convertToDTO(Contato contato){
        return ContatoDTO.builder()
                .id( contato.getId() )
                .nome(contato.getNome())
                .sobrenome(contato.getSobrenome())
                .email(contato.getEmail())
                .celular(contato.getCelular())
                .dataNascimento(contato.getDataNascimento())
                .build();
    }

}
