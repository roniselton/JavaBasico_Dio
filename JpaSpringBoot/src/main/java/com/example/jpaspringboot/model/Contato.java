package com.example.jpaspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_contato" , schema = "public")
public class Contato {

    @Id
    @Column(name = "id_contato")
    @GeneratedValue(generator = "seq_contato" , strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_contato" , sequenceName = "seq_contato",  schema = "public" , initialValue = 1 , allocationSize = 1)
    private Integer id;


    @NotNull(message = "Nome é obrigatório.")
    @Length(min = 3, message = "O nome deve ter no mínimo 3 caracteres.")
    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @NotNull(message = "E-mail é obrigatório.")
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "celular")
    private String celular;

    @Past(message = "Data de Nascimento inválida.")
    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "dt_cadastro")
    private LocalDateTime dataCadastro;

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                '}';
    }
}
