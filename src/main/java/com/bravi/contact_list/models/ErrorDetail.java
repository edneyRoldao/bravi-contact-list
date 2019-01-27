package com.bravi.contact_list.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetail {

    private LocalDateTime dateTime;
    private String message;
    private String details;

}
