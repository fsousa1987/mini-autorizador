package br.com.vr.mini_autorizador.api.exceptionhandler.exceptions;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException(String message) {
        super(message);
    }

}
