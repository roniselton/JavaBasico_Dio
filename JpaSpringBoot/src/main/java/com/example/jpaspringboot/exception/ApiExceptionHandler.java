package com.example.jpaspringboot.exception;

import com.example.jpaspringboot.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler<T> {

    @ExceptionHandler(value = { RegistroInvalidoException.class })
    protected ResponseEntity<Response<T>> handleTravelInvalidUpdateException(RegistroInvalidoException exception) {
        Response<T> response = new Response<>();
        response.addErrorMsg(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = { RegistroNotFoundException.class })
    protected ResponseEntity<Response<T>> handleTravelInvalidUpdateException(RegistroNotFoundException exception) {
        Response<T> response = new Response<>();
        response.addErrorMsg(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ResponseEntity<Response<T>> handleTravelInvalidUpdateException(MethodArgumentNotValidException exception) {
        Response<T> response = new Response<>();
        if(exception.getBindingResult().hasErrors()){
            exception.getBindingResult().getAllErrors().forEach(
                    error -> response.addErrorMsg(error.getDefaultMessage()));
        }else {
            response.addErrorMsg(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
