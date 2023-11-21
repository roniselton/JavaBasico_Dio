package com.example.jpaspringboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {

    @NotNull(message="Timestamp cannot be null.")
    private LocalDateTime timestamp;

    private String detail;

    private List<String> details;

    public void addMsgError(String msg){
//        if(CollectionUtils.isEmpty( getDetails() )){
        if(!CollectionUtils.isEmpty( getDetails() ) || StringUtils.hasText( getDetail() )){
            addMsgLista( msg );
        }else{
            setDetail(msg);
        }
    }

    private void addMsgLista(String msg) {
        if(details == null) {
            setDetails(new ArrayList<>());
            getDetails().add( detail );
            setDetail(null);
        }
        getDetails().add( msg );
    }

}
