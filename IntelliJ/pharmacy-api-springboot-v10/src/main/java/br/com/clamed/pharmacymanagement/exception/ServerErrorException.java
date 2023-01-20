package br.com.clamed.pharmacymanagement.exception;

import org.springframework.http.HttpStatus;

public class ServerErrorException extends RuntimeException{
    public ServerErrorException(){super();}

    public ServerErrorException(String message){super(message);}

    public ServerErrorException(String s, HttpStatus badRequest) {
    }
}
