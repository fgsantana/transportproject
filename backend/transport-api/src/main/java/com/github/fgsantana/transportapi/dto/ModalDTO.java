package com.github.fgsantana.transportapi.dto;

import com.github.fgsantana.transportapi.entity.ModalType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

public class ModalDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    @NotEmpty
    private ModalType tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModalType getTipo() {
        return tipo;
    }

    public void setTipo(ModalType tipo) {
        this.tipo = tipo;
    }
}
