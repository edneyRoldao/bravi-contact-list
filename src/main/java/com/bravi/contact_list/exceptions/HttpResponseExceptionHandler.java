package com.bravi.contact_list.exceptions;

import com.bravi.contact_list.models.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static com.bravi.contact_list.utils.ErrorMessages.UNEXPECTED_ERROR;

@RestController
@ControllerAdvice
public class HttpResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<ErrorDetail> handlePersonNotFound(PersonNotFoundException e, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public final ResponseEntity<ErrorDetail> handleContactNotFound(ContactNotFoundException e, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonAlreadyUsedException.class)
    public final ResponseEntity<ErrorDetail> handlePersonUsed(PersonAlreadyUsedException e, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ContactAlreadyUsedException.class)
    public final ResponseEntity<ErrorDetail> handleContactUsed(ContactAlreadyUsedException e, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<ErrorDetail> handleInvalidDataFormat(InvalidDataException e, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetail> handleUnexpectedExceptions(Exception e, WebRequest req) {

        // It is common to use log4j when an error occur, but to make things simple, I'm just printing the error on console.
        System.out.println(e.getMessage());

        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), UNEXPECTED_ERROR, req.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
