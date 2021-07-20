package com.zeroim.admin.util.BussinessExceptions;

import org.springframework.beans.factory.annotation.Autowired;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
