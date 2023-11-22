package com.example.jpaspringboot.exception;

import com.example.jpaspringboot.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
                    error -> {
                        if(error instanceof FieldError)
                            response.addErrorMsg( ((FieldError)error).getField() , error.getDefaultMessage());
                        else response.addErrorMsg( error.getDefaultMessage() );
                    });
        }else {
            response.addErrorMsg(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

}
