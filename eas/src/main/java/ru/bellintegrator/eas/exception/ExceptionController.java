package ru.bellintegrator.eas.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bellintegrator.eas.service.impl.OfficeServiceImpl;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    public ExceptionController() {
        super();
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> unhandledException(final Exception ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific " + ex;
        log.error("ExceptionController: " + ex);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
