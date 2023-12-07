package com.example.jpaspringboot.exception;

import com.example.jpaspringboot.dto.Response;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class ApiExceptionHandler<T> {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = { RegistroInvalidoException.class })
    protected ResponseEntity<Response<T>> handleTravelInvalidUpdateException(RegistroInvalidoException exception) {
        Response<T> response = new Response<>();
        response.addErrorMsg(exception.getMessage());
        ExceptionUtils.getRootCauseMessage(exception);
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

    @ExceptionHandler(value = { RepositoryConstraintViolationException.class })
    protected ResponseEntity<Response<T>> handleTravelInvalidUpdateException(RepositoryConstraintViolationException exception) {
        Response<T> response = new Response<>();
        if(exception.getErrors() != null && exception.getErrors().hasErrors()){
            exception.getErrors().getAllErrors().forEach(
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

    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity<Response<T>> handleTravelInvalidUpdateException(ConstraintViolationException exception) {
        Response<T> response = new Response<>();
        if(exception.getConstraintViolations() != null && !exception.getConstraintViolations().isEmpty() ){
            exception.getConstraintViolations().forEach(
                    constraintViolation ->  {
                        if(constraintViolation.getPropertyPath() !=null ) {
                            String atributo = constraintViolation.getPropertyPath().toString();
                            String chave = constraintViolation.getRootBeanClass().getSimpleName() +"."+ atributo;
                            String label = getLabelMessage(chave);
                            if(label == null)
                                label=atributo;
                            response.addErrorMsg(atributo, label + " "+constraintViolation.getMessage());

                        } else response.addErrorMsg( constraintViolation.getMessage() );
                    });
        }else {
            response.addErrorMsg(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private String getLabelMessage(String chave) {
        try{
            return messageSource.getMessage(chave, null , Locale.getDefault());
        }catch (Exception e) {
            return null;
        }
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
