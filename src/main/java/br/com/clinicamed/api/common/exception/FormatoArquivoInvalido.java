package br.com.clinicamed.api.common.exception;

public class FormatoArquivoInvalido extends RuntimeException {

    public FormatoArquivoInvalido() {
        super("O único formato de arquivos aceito para upload é o formato PDF.");
    }
}
