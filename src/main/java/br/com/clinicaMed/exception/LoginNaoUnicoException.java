package br.com.clinicaMed.exception;

public class LoginNaoUnicoException extends RuntimeException {

    public LoginNaoUnicoException() {
        super("O login informado já foi cadastrado no sistema.");
    }
}
