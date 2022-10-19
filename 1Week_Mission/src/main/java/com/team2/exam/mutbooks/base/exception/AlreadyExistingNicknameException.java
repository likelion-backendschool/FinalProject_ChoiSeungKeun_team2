package com.team2.exam.mutbooks.base.exception;

public class AlreadyExistingNicknameException extends RuntimeException{
    public AlreadyExistingNicknameException(String message) {
        super(message);
    }
}
