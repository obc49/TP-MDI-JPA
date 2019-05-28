package com.tpmdi.jpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound() { }

    public ResourceNotFound(String entity, Long id) {
        super(entity + " id " + id + " not found");
    }
    
}
