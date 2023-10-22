package com.example.testeconsumoviacep;

import com.example.testeconsumoviacep.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CepService cepService;

    @GetMapping
    public String teste(){
        return "here...";
    }

    @GetMapping("/cep/{numero}")
    public String consultarCep(@PathVariable("numero") String numero){
        return cepService.consultarCep( numero );
    }

}
