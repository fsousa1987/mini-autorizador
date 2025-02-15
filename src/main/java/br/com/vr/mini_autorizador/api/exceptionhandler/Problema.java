package br.com.vr.mini_autorizador.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {

    private final Integer status;
    private final OffsetDateTime timestamp;
    private final String tipo;
    private final String titulo;
    private final String detalhe;
    private final String mensagemParaUsuario;
    private final List<Object> objetos;

    @Getter
    @Builder
    public static class Object {

        private final String name;
        private final String userMessage;
    }

}
