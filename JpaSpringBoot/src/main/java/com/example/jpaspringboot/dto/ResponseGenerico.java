package com.example.jpaspringboot.dto;

import lombok.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseGenerico {

    private Map<String,Object> map;

    private ResponseGenerico(){
        map = new LinkedHashMap<>();
    }

    public static ResponseGenerico builder(){
        return new ResponseGenerico();
    }

    public ResponseGenerico add(String chave,Object valor){
        map.put(chave , valor);
        return this;
    }

    public Response<Map<String,Object>> build(){
        return new Response<>( map , null);
    }
}
