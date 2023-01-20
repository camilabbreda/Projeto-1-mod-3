package br.com.clamed.pharmacymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class BadRequestException extends RuntimeException {

    public BadRequestException(){
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }


}
