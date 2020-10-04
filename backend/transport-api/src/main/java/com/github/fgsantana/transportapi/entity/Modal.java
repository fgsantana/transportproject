package com.github.fgsantana.transportapi.entity;

public enum Modal {
    Ferroviario("Ferroviário"),
    Rodoviario("Rodoviário"),
    Hidroviario("Hidroviário"),
    Dutoviario("Dutoviário"),
    Aeroviario("Aeroviário");


    public String getTipo() {
        return tipo;
    }

    Modal(String tipo) {
        this.tipo = tipo;
    }

    private String tipo;


}
