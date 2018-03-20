package br.com.clinicamed.api.common.exception;

public class NomeFabricaNaoUnicoException extends RuntimeException {

    public NomeFabricaNaoUnicoException() {
        super("O nome de fábrica informado já foi cadastrado no sistema.");
    }
}
