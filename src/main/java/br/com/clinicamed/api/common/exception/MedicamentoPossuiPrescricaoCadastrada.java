package br.com.clinicamed.api.common.exception;

public class MedicamentoPossuiPrescricaoCadastrada extends RuntimeException {

    public MedicamentoPossuiPrescricaoCadastrada() {
        super("Não é possível excluir o cadastro do medicamento, pois o mesmo possui pelo menos uma prescrição cadastrada.");
    }
}
