package com.example.jpaspringboot.controller;

import com.example.jpaspringboot.dto.Response;
import com.example.jpaspringboot.dto.ResponseGenerico;
import com.example.jpaspringboot.exception.RegistroNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @GetMapping
    public String ola(){
        return "Ola.";
    }

    @GetMapping("/responsegenerico")
    public ResponseEntity<Response<Map<String,Object>>> testeResponseGenerico(@RequestParam(required = false , name = "type") String type) throws RegistroNotFoundException {
        if(StringUtils.hasText( type )){
            if(type.equalsIgnoreCase( "color")) {
                return ResponseEntity.ok(ResponseGenerico.builder()
                        .add("red", "#FF5733")
                        .add("yellow", "#FFC300")
                        .add("blue", "#6C95D3")
                        .add("green", "#59A53C")
                        .add("white", "#FFFFFF")
                        .add("black", "#000000").build());
            }
            else{
                throw new RegistroNotFoundException();
            }
        }else {
            return ResponseEntity.ok(ResponseGenerico.builder()
                    .add("par",2L)
                    .add("impar",3L).build());
        }
    }
}
