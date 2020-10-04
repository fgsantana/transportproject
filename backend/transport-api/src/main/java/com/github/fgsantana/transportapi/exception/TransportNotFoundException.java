package com.github.fgsantana.transportapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransportNotFoundException extends RuntimeException {
    public TransportNotFoundException(Long id){
        super("Transportadora com id " + id + " não encontrada!");
    }
}
