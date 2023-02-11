package mira.space.kameleoon.exceptions.advices;

import mira.space.kameleoon.exceptions.PropertyBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler(PropertyBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String propertyBadRequestHandler(PropertyBadRequestException ex) {
        return ex.getMessage();
    }
}
