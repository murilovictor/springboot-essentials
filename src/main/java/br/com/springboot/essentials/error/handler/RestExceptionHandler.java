package br.com.springboot.essentials.error.handler;

import br.com.springboot.essentials.error.ResourceNotFoundDetails;
import br.com.springboot.essentials.error.ResourceNotFoundException;
import br.com.springboot.essentials.error.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException manvException){

        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
        String collectField = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String collectFieldError = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationErrorDetails validationErrorDetails = ValidationErrorDetails
                .builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field Validation Error")
                .detail("Field Validation Error")
                .developerMessage(manvException.getClass().getName())
                .field(collectField)
                .fieldMessage(collectFieldError)
                .build();
        return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);
    }


}

