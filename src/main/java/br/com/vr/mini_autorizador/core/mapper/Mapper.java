package br.com.vr.mini_autorizador.core.mapper;

import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public CartaoEntity transformarCartaoRequestEmCartaoEntity(CartaoRequest cartaoRequest) {
        CartaoEntity cartaoEntity = new CartaoEntity();
        cartaoEntity.setNumeroCartao(cartaoRequest.numeroCartao());
        cartaoEntity.setSenha(cartaoRequest.senha());
        return cartaoEntity;
    }

    public CartaoResponse transformarCartaoEntityEmCartaoResponse(CartaoEntity cartaoEntity) {
        return new CartaoResponse(cartaoEntity.getSenha(), cartaoEntity.getNumeroCartao());
    }

}
