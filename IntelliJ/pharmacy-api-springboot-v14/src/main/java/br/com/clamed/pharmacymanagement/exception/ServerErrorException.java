package br.com.clamed.pharmacymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServerErrorException extends RuntimeException{
    private static final long   serialVersionUID=1;
    public ServerErrorException(){super();}

    public ServerErrorException(String message){super(message);}

    public ServerErrorException(String s, HttpStatus badRequest) {
    }
}
