package mira.space.kameleoon.exceptions;

public class PropertyBadRequestException extends RuntimeException {
    public PropertyBadRequestException(String propertyName) {
        super("Incorrect property " + propertyName + ", check for null or emptiness");
    }
}
