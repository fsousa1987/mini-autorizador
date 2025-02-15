package br.com.vr.mini_autorizador.api.exceptionhandler.exceptions;

public class CartaoNaoEncontradoException extends RuntimeException {

    public CartaoNaoEncontradoException(String message) {
        super(message);
    }

}
