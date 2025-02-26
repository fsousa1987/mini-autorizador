package br.com.vr.mini_autorizador.factory;

import br.com.vr.mini_autorizador.domain.dto.request.TransacaoRequest;

import java.math.BigDecimal;

public class TransacaoFactory {

    public static TransacaoRequest criarUmaTransacaoRequest() {
        return TransacaoRequest.builder()
                .numeroCartao("6549873025634501")
                .senhaCartao("1234")
                .valor(BigDecimal.valueOf(1000))
                .build();
    }

}
