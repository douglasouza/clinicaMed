package br.com.clinicaMed.api.common.exception;

public class CrmNaoUnicoException extends RuntimeException {

    public CrmNaoUnicoException() {
        super("O CRM informado já foi cadastrado no sistema.");
    }
}
