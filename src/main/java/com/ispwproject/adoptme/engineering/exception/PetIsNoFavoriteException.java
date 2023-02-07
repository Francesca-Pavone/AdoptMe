package com.ispwproject.adoptme.engineering.exception;

public class PetIsNoFavoriteException extends Exception{
    public PetIsNoFavoriteException(int petId, int userId) {
        super("Pet with id: " + petId + " is not a user's with id: " + userId + " favorite");
    }
}
