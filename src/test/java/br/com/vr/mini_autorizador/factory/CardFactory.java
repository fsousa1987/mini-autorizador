package br.com.vr.mini_autorizador.factory;

import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;

import java.math.BigDecimal;

public class CardFactory {

    public static CartaoRequest criarUmCartaoRequest() {
        return CartaoRequest.builder()
                .numeroCartao("1234")
                .senha("1234567890123456")
                .build();
    }

    public static CartaoEntity criarUmCartaoEntity() {
        return CartaoEntity.builder()
                .numeroCartao("1234567890123456")
                .senha("1234")
                .saldo(BigDecimal.valueOf(500))
                .build();
    }

}
