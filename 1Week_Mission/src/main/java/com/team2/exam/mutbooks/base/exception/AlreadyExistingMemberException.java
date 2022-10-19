package com.team2.exam.mutbooks.base.exception;

public class AlreadyExistingMemberException extends RuntimeException{
    public AlreadyExistingMemberException(String message) {
        super(message);
    }
}
