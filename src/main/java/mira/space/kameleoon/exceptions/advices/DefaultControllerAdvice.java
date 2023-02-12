package mira.space.kameleoon.exceptions.advices;

import mira.space.kameleoon.exceptions.EntityUpdateException;
import mira.space.kameleoon.exceptions.IncorrectParamException;
import mira.space.kameleoon.exceptions.IncorrectPropertyException;
import mira.space.kameleoon.exceptions.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler(IncorrectPropertyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String incorrectPropertyHandler(IncorrectPropertyException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(EntityUpdateException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String entityUpdateException(EntityUpdateException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NoSuchEntityException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noSuchEntityHandler(NoSuchEntityException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(IncorrectParamException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String incorrectParamException(IncorrectParamException ex) {
        return ex.getMessage();
    }
}
