package br.com.clamed.pharmacymanagement.controllers.handler;


import br.com.clamed.pharmacymanagement.exception.BadRequestException;
import br.com.clamed.pharmacymanagement.exception.NotFoundException;
import br.com.clamed.pharmacymanagement.exception.ServerErrorException;
import br.com.clamed.pharmacymanagement.padronizacaoErro.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class GlobalHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException> tratarNotFoundException(Exception e){

        return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity tratarBadRequestException(Exception e){
        return new ResponseEntity(
                new DefaultErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        e.getCause()),

                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity tratarServerSideException(Exception e){
        return new ResponseEntity(
                new DefaultErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        e.getCause()
                ),

                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarMethodArgumentNotValidException(Exception e){
        return new ResponseEntity(
                new DefaultErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        e.getCause()
                ),

                HttpStatus.BAD_REQUEST);
    }
}
