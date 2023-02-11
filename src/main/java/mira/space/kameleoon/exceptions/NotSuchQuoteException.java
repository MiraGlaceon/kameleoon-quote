package mira.space.kameleoon.exceptions;

public class NotSuchQuoteException extends RuntimeException{
    public NotSuchQuoteException(Long id) {
        super("Quote by id = " + id + ", not found");
    }
}
