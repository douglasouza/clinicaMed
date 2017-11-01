package br.com.clinicaMed.api.common.exception;

public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException() {
        super("O CPF informado é inválido.");
    }
}
