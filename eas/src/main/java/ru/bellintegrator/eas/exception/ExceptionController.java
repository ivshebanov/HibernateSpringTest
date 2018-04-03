package ru.bellintegrator.eas.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handlerException(HttpServletRequest request, Exception e) {
        log.error("Request: " + request.getRequestURL() + " raised " + e);
        return new ResponseEntity<>("erorr", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
