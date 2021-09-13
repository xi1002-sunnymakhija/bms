
package com.example.bms.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.PARTIAL_CONTENT;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {


    private static final Logger LOGGING = LoggerFactory.getLogger(CustomizedExceptionHandler.class);
    private static final String EXCEPTION_MESSAGE = "Request failure due to Exception {} : {} occurred.";
    private static String BAD_FILTER="101";


    @ExceptionHandler(SearchException.class)
    public final ResponseEntity handleSearchCriteriaException(SearchException ex, WebRequest request) {
        LOGGING.error(EXCEPTION_MESSAGE, ex.getClass(), ex);
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), BAD_FILTER);
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);

    }

}

