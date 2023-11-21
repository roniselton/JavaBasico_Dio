package com.example.jpaspringboot;

import com.example.jpaspringboot.model.Usuario;
import com.example.jpaspringboot.enumerados.TipoPessoa;
import com.example.jpaspringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartApp implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Salvando Usuario ----");
//        Usuario u = Usuario.builder()
//                .nome("Roniselton Silva")
//                .email("ronibarreto@gmail.com")
//                .tipoPessoa(TipoPessoa.PF)
//                .cpfCnpj("88928039134")
//                .senha("123456")
//                .build();
//        u.setLogin( u.getEmail() );
//        usuarioRepository.save( u );
//        System.out.println("Usuario  salvo----");
//
//        for (Usuario usuario:usuarioRepository.findAll()){
//            System.out.println( usuario );
//        }
    }
}
