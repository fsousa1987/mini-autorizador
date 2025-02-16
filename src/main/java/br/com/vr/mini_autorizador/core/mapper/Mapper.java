package br.com.vr.mini_autorizador.core.mapper;

import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public CartaoEntity transformarCartaoRequestEmCartaoEntity(CartaoRequest cartaoRequest) {
        return CartaoEntity.builder()
                .numeroCartao(cartaoRequest.numeroCartao())
                .senha(cartaoRequest.senha())
                .build();
    }

    public CartaoResponse transformarCartaoEntityEmCartaoResponse(CartaoEntity cartaoEntity) {
        return CartaoResponse.builder()
                .senha(cartaoEntity.getSenha())
                .numeroCartao(cartaoEntity.getNumeroCartao())
                .build();
    }

}
