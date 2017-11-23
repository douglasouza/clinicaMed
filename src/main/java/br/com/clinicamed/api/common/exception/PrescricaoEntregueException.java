package br.com.clinicamed.api.common.exception;

public class PrescricaoEntregueException extends RuntimeException {

    public PrescricaoEntregueException() {
        super("A prescrição já foi entregue ao paciente.");
    }
}
