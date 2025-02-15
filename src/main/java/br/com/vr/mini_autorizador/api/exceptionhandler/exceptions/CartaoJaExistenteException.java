package br.com.vr.mini_autorizador.api.exceptionhandler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class CartaoJaExistenteException extends RuntimeException {

    public CartaoJaExistenteException(String message) {
        super(message);
    }

}
