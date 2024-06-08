package com.medialab.rental.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UsernameTakenException extends ResponseStatusException {
    public UsernameTakenException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public static UsernameTakenException of(String username) {
        return new UsernameTakenException(HttpStatus.BAD_REQUEST, "Username taken: " + username);
    }

}
