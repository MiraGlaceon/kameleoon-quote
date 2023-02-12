package mira.space.kameleoon.exceptions;

public class UserSessionIdException extends RuntimeException{
    public UserSessionIdException() {
        super("User does not exist. Check compliance sessionId and user entity, probably");
    }
}
