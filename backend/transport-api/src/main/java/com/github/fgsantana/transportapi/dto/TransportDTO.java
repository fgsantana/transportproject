package com.github.fgsantana.transportapi.dto;


import com.github.fgsantana.transportapi.entity.Modal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class TransportDTO {
    private Long id;


    @Email
    @NotEmpty
    private String email;

    @Size(min = 4)
    @NotEmpty
    private String nome;

    @NotEmpty
    private String empresa;


    @NotEmpty
    @Size(min = 10, max = 11)
    private String telefone;

    @Size(min = 10, max = 11)
    private String celular;

    @Size(min = 10, max = 11)
    private String whatsapp;

    @NotEmpty
    private List<Modal> modais;

    @Size(min = 8, max = 8)
    private String cep;

    @NotEmpty
    @Size(min = 2, max = 2)
    private String uf;

    @NotEmpty
    private String cidade;

    @NotEmpty
    private String bairro;

    @NotEmpty
    private String logradouro;

    private String logoUrl;

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    private int numero;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public List<Modal> getModais() {
        return modais;
    }

    public void setModais(List<Modal> modais) {
        this.modais = modais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportDTO)) return false;
        TransportDTO that = (TransportDTO) o;
        return numero == that.numero &&
                id.equals(that.id) &&
                email.equals(that.email) &&
                nome.equals(that.nome) &&
                empresa.equals(that.empresa) &&
                telefone.equals(that.telefone) &&
                Objects.equals(celular, that.celular) &&
                Objects.equals(whatsapp, that.whatsapp) &&
                modais.equals(that.modais) &&
                cep.equals(that.cep) &&
                uf.equals(that.uf) &&
                cidade.equals(that.cidade) &&
                Objects.equals(bairro, that.bairro) &&
                logradouro.equals(that.logradouro) &&
                Objects.equals(logoUrl, that.logoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, nome, empresa, telefone, celular, whatsapp, modais, cep, uf, cidade, bairro, logradouro, logoUrl, numero);
    }
}
