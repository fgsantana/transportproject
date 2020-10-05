package com.github.fgsantana.transportapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCepFormatException extends Exception{
    public InvalidCepFormatException(){
        super("Formato de CEP inválido! Deve ser:  xxxxxxxx");
    }
}
