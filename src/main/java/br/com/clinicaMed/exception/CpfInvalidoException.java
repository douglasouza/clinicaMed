package br.com.clinicaMed.exception;

public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException() {
        super("O CPF informado é inválido.");
    }
}
