package com.nolimits.clinica.exception;

public class ModelNotFoundException extends RuntimeException{

    // Recibe el mensaje que luego será usado en el @ExceptionHandler
    public ModelNotFoundException(String message) {
        super(message);
    }
}
