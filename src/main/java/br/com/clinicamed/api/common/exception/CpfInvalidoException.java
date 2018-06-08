package br.com.clinicamed.api.common.exception;

public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException() {
        super("O CPF informado é inválido.");
    }
}
