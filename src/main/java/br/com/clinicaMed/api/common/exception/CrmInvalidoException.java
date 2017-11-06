package br.com.clinicaMed.api.common.exception;

public class CrmInvalidoException extends RuntimeException {

    public CrmInvalidoException() {
        super("O CRM informado é inválido.");
    }
}
