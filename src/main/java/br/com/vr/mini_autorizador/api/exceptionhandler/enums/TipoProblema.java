package br.com.vr.mini_autorizador.api.exceptionhandler.enums;

import lombok.Getter;

@Getter
public enum TipoProblema {

    RECURSO_JA_EXISTE("Recurso já existe");

    private final String title;

    TipoProblema(String title) {
        this.title = title;
    }

}
