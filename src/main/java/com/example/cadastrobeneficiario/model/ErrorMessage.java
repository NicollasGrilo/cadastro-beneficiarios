package com.example.cadastrobeneficiario.model;

import jakarta.el.BeanELResolver;

public class ErrorMessage {

    private Beneficiario beneficiario;
    private String message;

    public ErrorMessage(){

    }

    public ErrorMessage(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
