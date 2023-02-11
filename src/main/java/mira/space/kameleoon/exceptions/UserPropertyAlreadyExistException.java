package mira.space.kameleoon.exceptions;

public class UserPropertyAlreadyExistException extends RuntimeException{
    public UserPropertyAlreadyExistException(String propertyName) {
        super("User with this " + propertyName + " already exist");
    }
}
