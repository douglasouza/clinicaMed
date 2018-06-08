package br.com.clinicamed.api.common.exception;

public class PacientePossuiPrescricaoCadastrada extends RuntimeException {

    public PacientePossuiPrescricaoCadastrada() {
        super("Não é possível excluir o cadastro do paciente, pois o mesmo possui pelo menos uma prescrição de medicamento cadastrada.");
    }
}
