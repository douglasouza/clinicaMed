package br.com.clinicaMed.api.common.exception;

public class CpfNaoUnicoException extends RuntimeException {

    public CpfNaoUnicoException() {
        super("O CPF informado já foi cadastrado no sistema.");
    }
}
