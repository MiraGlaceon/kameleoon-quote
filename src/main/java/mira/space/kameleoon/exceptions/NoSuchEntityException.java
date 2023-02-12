package mira.space.kameleoon.exceptions;

public class NoSuchEntityException extends RuntimeException{
    public NoSuchEntityException(Long id) {
        super("Entity by id = " + id + " not found");
    }
}
