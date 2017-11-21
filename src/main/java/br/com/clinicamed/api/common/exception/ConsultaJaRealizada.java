package br.com.clinicamed.api.common.exception;

public class ConsultaJaRealizada extends RuntimeException {

    public ConsultaJaRealizada() {
        super("Não é possível excluir a consulta pois a mesma já foi realizada.");
    }
}
