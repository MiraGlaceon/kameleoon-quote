package mira.space.kameleoon.exceptions;

public class IncorrectPropertyException extends RuntimeException {
    public IncorrectPropertyException(String propertyName) {
        super("Incorrect property " + propertyName + ", check for null or emptiness");
    }
}
