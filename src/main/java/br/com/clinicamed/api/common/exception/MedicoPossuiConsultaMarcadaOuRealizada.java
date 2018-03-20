package br.com.clinicamed.api.common.exception;

public class MedicoPossuiConsultaMarcadaOuRealizada extends RuntimeException {

    public MedicoPossuiConsultaMarcadaOuRealizada() {
        super("Não é possível excluir o cadastro do médico, pois o mesmo possui consulta marcada ou já realizada.");
    }
}
