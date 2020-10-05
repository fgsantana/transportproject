package com.github.fgsantana.transportapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CepNotFoundException extends Exception {
    public CepNotFoundException(Long cep){
        super("Cep " + cep + " não encontrado!" );
    }
}
