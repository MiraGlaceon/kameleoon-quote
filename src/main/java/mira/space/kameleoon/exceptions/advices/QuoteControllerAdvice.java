package mira.space.kameleoon.exceptions.advices;

import mira.space.kameleoon.exceptions.NotSuchQuoteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuoteControllerAdvice {

    @ExceptionHandler(NotSuchQuoteException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notSuchQuoteHandler(NotSuchQuoteException ex) {
        return ex.getMessage();
    }
}
