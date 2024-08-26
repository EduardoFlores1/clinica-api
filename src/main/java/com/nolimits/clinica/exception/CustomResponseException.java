package com.nolimits.clinica.exception;

public class CustomResponseException extends RuntimeException{

    // Recibe el mensaje que luego será usado en el @ExceptionHandler
    public CustomResponseException(String msg) {
        super(msg);
    }
}
