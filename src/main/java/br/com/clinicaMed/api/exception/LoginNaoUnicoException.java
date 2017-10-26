package br.com.clinicaMed.api.exception;

public class LoginNaoUnicoException extends RuntimeException {

    public LoginNaoUnicoException() {
        super("O login informado já foi cadastrado no sistema.");
    }
}
