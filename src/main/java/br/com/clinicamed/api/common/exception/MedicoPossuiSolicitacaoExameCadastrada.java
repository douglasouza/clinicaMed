package br.com.clinicamed.api.common.exception;

public class MedicoPossuiSolicitacaoExameCadastrada extends RuntimeException {

    public MedicoPossuiSolicitacaoExameCadastrada() {
    super("Não é possível excluir o cadastro do médico, pois o mesmo cadastrou pelo menos uma solicitação de exame.");
    }
}
