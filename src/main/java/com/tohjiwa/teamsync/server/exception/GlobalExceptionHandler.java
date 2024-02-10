package com.tohjiwa.teamsync.server.exception;

import com.tohjiwa.teamsync.server.model.RestResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidRequestException.class, ConstraintViolationException.class})
    public ResponseEntity<RestResponseBody<?>> handleInvalidRequestException(HttpServletRequest req, Exception ex) {
        var restResponseBody = new RestResponseBody<>(400, null);
        restResponseBody.setMessage(ex.getMessage());
        restResponseBody.setError("Bad Request");
        restResponseBody.setPath(req.getRequestURI());
        return new ResponseEntity<>(restResponseBody, HttpStatus.BAD_REQUEST);
    }

}
