package br.com.clamed.pharmacymanagement.exception;


import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){super();}

    public NotFoundException(String message){super(message);}

    public NotFoundException(String s, HttpStatus noContent) {
    }
}
