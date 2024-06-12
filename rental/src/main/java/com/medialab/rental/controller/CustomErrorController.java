package com.medialab.rental.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity<Void> handleError(HttpServletRequest request){
        Object httpStatus = request.getAttribute(ERROR_STATUS_CODE);

        int statusCode;
        try {
            statusCode = httpStatus == null ? -1 : Integer.parseInt(httpStatus.toString());
        } catch (NumberFormatException e){
            statusCode = -1;
        }

        String redirect = switch(statusCode){
            case 403 -> "/unauthorized.html";
            default -> "/error.html";
        };

        return ResponseEntity
                .status(HttpStatus.TEMPORARY_REDIRECT)
                .header("Location", redirect)
                .build();
    }
}
