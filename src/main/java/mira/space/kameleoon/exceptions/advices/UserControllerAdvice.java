package mira.space.kameleoon.exceptions.advices;

import mira.space.kameleoon.exceptions.UserPropertyAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserPropertyAlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userPropertyAlreadyExistHandler(UserPropertyAlreadyExistException ex) {
        return ex.getMessage();
    }
}
