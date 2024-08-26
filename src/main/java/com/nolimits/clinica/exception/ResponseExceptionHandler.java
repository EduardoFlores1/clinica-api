package com.nolimits.clinica.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Date;

// @RestControllerAdvice se utiliza para proporcionar un punto de entrada global para el manejo
// de excepciones en controladores REST
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    // Gestiona todas las excepciones
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> allExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Retorna NOT_FOUND, al no encontrarse el modelo
    @ExceptionHandler(ModelNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> modelNotFound(ModelNotFoundException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);
    }

    // Puede retornar una lista de validaciones
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), Arrays.toString(ex.getDetailMessageArguments()), request.getDescription(false));
        return new ResponseEntity<>(exResponse, HttpStatus.BAD_REQUEST);
    }

    // Retorna NOT_ACCEPTABLE, para un caso excepcional
    @ExceptionHandler(CustomResponseException.class)
    public final ResponseEntity<ExceptionResponse> customResponse(CustomResponseException ex, WebRequest request) {
        ExceptionResponse exResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
