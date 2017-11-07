package br.com.clinicamed.api.common.exception;

public class LoginNaoUnicoException extends RuntimeException {

    public LoginNaoUnicoException() {
        super("O login informado já foi cadastrado no sistema.");
    }
}
