package br.com.clamed.pharmacymanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long   serialVersionUID=1;

    public NotFoundException(){super();}

    public NotFoundException(String message){super(message);}

    public NotFoundException(String s, HttpStatus noContent) {
    }

}
