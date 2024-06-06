package com.medialab.rental.controller;

import com.medialab.rental.service.UsernameTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value= { UsernameTakenException.class })
    protected UsernameTakenException handleUsernameTakenException(UsernameTakenException e) {
        return e;
    }
}
