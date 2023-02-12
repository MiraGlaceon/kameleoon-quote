package mira.space.kameleoon.exceptions;

public class IncorrectParamException extends RuntimeException {
    public IncorrectParamException(String param) {
        super("Incorrect property " + param + ", check for null or emptiness");
    }
}
