package br.com.clinicamed.api.common.exception;

public class SolicitacaoExamePossuiResultadoException extends RuntimeException {

    public SolicitacaoExamePossuiResultadoException() {
        super("A solicitação de exame já possui resultado entregue.");
    }
}
