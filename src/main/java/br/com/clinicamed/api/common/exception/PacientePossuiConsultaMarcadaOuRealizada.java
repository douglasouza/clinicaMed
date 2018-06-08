package br.com.clinicamed.api.common.exception;

public class PacientePossuiConsultaMarcadaOuRealizada extends RuntimeException {

    public PacientePossuiConsultaMarcadaOuRealizada() {
        super("Não é possível excluir o cadastro do paciente, pois o mesmo possui consulta marcada ou já realizada.");
    }
}
