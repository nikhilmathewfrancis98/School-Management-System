package com.kenschool.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
@ControllerAdvice is a specialization of the @Component annotation which allows to handle
exceptions across the whole application in one global handling component. It can be viewed
as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
* */
@Slf4j
@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(Exception.class)
    public ModelAndView GlobalErrorPage(Exception e) {
        String errorMsg=null;
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error.html");
        if(e.getMessage()!=null){
            errorMsg=e.getMessage();
        } else if (e.getCause()!=null) {
            errorMsg=e.getCause().toString();
        }else if(e!=null){
            errorMsg=e.toString();
        }
        // Always we are adding the attributes to Model class no we added the error message to modelandview
        errorPage.addObject("errormsg", errorMsg);
        return errorPage;
    }
}
