package com.example.jpaspringboot.model;

import com.example.jpaspringboot.enumerados.TipoCategoriaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_categoria" , schema = "public")
public class Categoria {

    @Id
    @Column(name = "id_categoria")
    @GeneratedValue(generator = "seq_categoria" , strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_categoria" , sequenceName = "seq_categoria",  schema = "public" , initialValue = 1 , allocationSize = 1)
    private Integer id;


    @NotBlank(message = "Descrição é obrigatória.")
    @Length(min = 3, message = "deve ter no mínimo 3 caracteres.")
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoCategoriaEnum tipo;

    // Adicionando valores default
//    @Column(name = "ativo")
//    private Boolean ativo = true;

    @Column(name = "ativo", columnDefinition = "boolean default false" )
    private Boolean ativo;

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
