package br.com.vr.mini_autorizador.factory;

import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;

import java.math.BigDecimal;

public class CartaoFactory {

    public static CartaoRequest criarUmCartaoRequest() {
        return CartaoRequest.builder()
                .numeroCartao("6549873025634501")
                .senha("1234")
                .build();
    }

    public static CartaoEntity criarUmCartaoEntity() {
        return CartaoEntity.builder()
                .numeroCartao("6549873025634501")
                .senha("1234")
                .saldo(BigDecimal.valueOf(500))
                .build();
    }

    public static CartaoResponse criarUmCartaoResponse() {
        return CartaoResponse.builder()
                .senha("1234")
                .numeroCartao("6549873025634501")
                .build();
    }

}
