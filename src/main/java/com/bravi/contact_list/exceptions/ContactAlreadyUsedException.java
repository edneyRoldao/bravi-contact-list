package com.bravi.contact_list.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ContactAlreadyUsedException extends RuntimeException {

    public ContactAlreadyUsedException(String message) {
        super(message);
    }

}
