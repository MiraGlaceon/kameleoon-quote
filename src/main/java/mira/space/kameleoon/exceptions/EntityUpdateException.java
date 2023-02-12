package mira.space.kameleoon.exceptions;

public class EntityUpdateException extends RuntimeException{
    public EntityUpdateException(String entityName, Long id) {
        super("Could not update " + entityName + " in database. Entity id = " + id);
    }
}
