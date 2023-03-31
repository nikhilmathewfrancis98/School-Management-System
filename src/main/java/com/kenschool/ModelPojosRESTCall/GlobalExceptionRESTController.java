package com.kenschool.ModelPojosRESTCall;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestController.class) // This global exception class will work for all the Class with
//RestController annotation
@Order(1)// For giving the first priority
public class GlobalExceptionRESTController extends ResponseEntityExceptionHandler {
// ResponseEntityExceptionHandler Class has many methods Explore them
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request){
            ResponseEntityRest response=new ResponseEntityRest(status.toString(),
                    ex.getBindingResult().toString());
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseEntityRest> RESTGlobalException(Exception e){
        ResponseEntityRest response=new ResponseEntityRest("500",e.getMessage());
        return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
