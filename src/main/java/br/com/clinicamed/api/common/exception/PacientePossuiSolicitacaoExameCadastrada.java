package br.com.clinicamed.api.common.exception;

public class PacientePossuiSolicitacaoExameCadastrada extends RuntimeException {

    public PacientePossuiSolicitacaoExameCadastrada() {
    super("Não é possível excluir o cadastro do paciente, pois o mesmo possui pelo menos uma solicitação de exame cadastrada.");
    }
}
