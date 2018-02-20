package br.com.clinicamed.api.common.exception;

public class DataHoraConsultaInvalidaException extends RuntimeException {

    public DataHoraConsultaInvalidaException() {
        super("A data e horário informados para a consulta ocorre antes da data/hora atual. Informe uma data/hora válida.");
    }
}
