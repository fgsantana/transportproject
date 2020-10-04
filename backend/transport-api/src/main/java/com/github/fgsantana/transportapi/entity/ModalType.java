package com.github.fgsantana.transportapi.entity;

public enum ModalType {
    Ferroviario("Ferroviário"),
    Rodoviario("Rodoviário"),
    Hidroviario("Hidroviário"),
    Dutoviario("Dutoviário"),
    Aeroviario("Aeroviário");


    public String getTipo() {
        return tipo;
    }

    ModalType(String tipo) {
        this.tipo = tipo;
    }

    private String tipo;


}
