package br.com.vr.mini_autorizador.api.exceptionhandler.exceptions;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String message) {
        super(message);
    }

}
