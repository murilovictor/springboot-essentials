package br.com.springboot.essentials.error.handler;

import br.com.springboot.essentials.error.ResourceNotFoundDetails;
import br.com.springboot.essentials.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * @author Murilo Victor on 12/06/2019
 */
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException){
        ResourceNotFoundDetails resourceNotFoundDetails = ResourceNotFoundDetails
                .builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getName())
                .build();
        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);
    }
}
