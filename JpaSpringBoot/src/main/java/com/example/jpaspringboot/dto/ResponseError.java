package com.example.jpaspringboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {

    @NotNull(message="Timestamp cannot be null.")
    private LocalDateTime timestamp;

    private String field;

    private String detail;

    private Map<String,String> details;

    public void addMsgError(String field , String msg){
//        if(CollectionUtils.isEmpty( getDetails() )){
        if(!CollectionUtils.isEmpty( getDetails() ) || StringUtils.hasText( getDetail() )){
            addMsgLista( field ,  msg );
        }else{
            setDetail(msg);
            setField(field);
        }
    }

    private void addMsgLista(String field , String msg) {
        if(details == null) {
            setDetails(new LinkedHashMap<>());
            getDetails().put( this.field , this.detail );
            setDetail(null);
            setField( null);
        }
        getDetails().put( field , msg );
    }

}
