package com.example.jpaspringboot.exception;


public class RegistroNotFoundException extends Exception {

    public RegistroNotFoundException(){
        super("Registro não encontrado.");
    }

}
