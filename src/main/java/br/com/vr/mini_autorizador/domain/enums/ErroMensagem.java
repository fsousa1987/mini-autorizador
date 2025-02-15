package br.com.vr.mini_autorizador.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErroMensagem {

    SENHA_INVALIDA("SENHA_INVALIDA"),
    SALDO_INSUFICIENTE("SALDO_INSUFICIENTE"),
    CARTAO_INEXISTENTE("CARTAO_INEXISTENTE");

    private final String mensagem;

}
