package com.example.jpaspringboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;

    private ResponseError error;

    public void addErrorMsg(String msg){
       addErrorMsg( "" , msg);
    }

    public void addErrorMsg(String field , String msg){
        if(getError() == null)
            setError( ResponseError.builder().timestamp(LocalDateTime.now()).build() );
        getError().addMsgError( field , msg );
    }

}
