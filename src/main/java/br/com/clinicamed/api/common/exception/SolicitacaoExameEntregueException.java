package br.com.clinicamed.api.common.exception;

public class SolicitacaoExameEntregueException extends RuntimeException {

    public SolicitacaoExameEntregueException() {
        super("A solicitação de exame já foi entregue ao paciente.");
    }
}
