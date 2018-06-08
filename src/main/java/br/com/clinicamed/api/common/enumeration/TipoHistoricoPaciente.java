package br.com.clinicamed.api.common.enumeration;

public enum TipoHistoricoPaciente {
    CONSULTA_MARCACAO ("Marcação de Consulta"),
    CONSULTA_REALIZADA ("Consulta Realizada"),
    PACIENTE ("Paciente Cadastrado"),
    PRESCRICAO ("Prescrição"),
    SOLIC_EXAME_CADASTRO ("Solicitação de Exame"),
    SOLIC_EXAME_ENTREGUE ("Entrega de Resultado de Exame");

    private String tipoHistorico;

    TipoHistoricoPaciente(String tipoHistorico) {
        this.tipoHistorico = tipoHistorico;
    }

    public String getTipoHistorico() {
        return tipoHistorico;
    }
}
