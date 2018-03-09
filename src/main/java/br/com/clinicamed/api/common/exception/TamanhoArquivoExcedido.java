package br.com.clinicamed.api.common.exception;

public class TamanhoArquivoExcedido extends RuntimeException {

    public TamanhoArquivoExcedido() {
        super("O tamanho do arquivo excedeu o limite permitido pelo sistema.");
    }
}
