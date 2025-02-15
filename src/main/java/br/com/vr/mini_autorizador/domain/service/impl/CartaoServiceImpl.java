package br.com.vr.mini_autorizador.domain.service.impl;

import br.com.vr.mini_autorizador.core.mapper.Mapper;
import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import br.com.vr.mini_autorizador.domain.repository.CartaoRepository;
import br.com.vr.mini_autorizador.domain.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartaoServiceImpl implements CartaoService {

    private final CartaoRepository cartaoRepository;
    private final Mapper mapper;

    @Override
    public CartaoResponse criarCartao(CartaoRequest cartaoRequest) {
        CartaoEntity cartaoEntity = mapper.transformarCartaoRequestEmCartaoEntity(cartaoRequest);

        cartaoEntity = cartaoRepository.save(cartaoEntity);

        return mapper.transformarCartaoEntityEmCartaoResponse(cartaoEntity);
    }

    @Override
    public BigDecimal verificarSaldo(String cardNumber) {
        return null;
    }

    @Override
    public Optional<CartaoResponse> verificarSeCartaoExiste(CartaoRequest cartaoRequest) {
        return cartaoRepository.findById(cartaoRequest.numeroCartao())
                .map(mapper::transformarCartaoEntityEmCartaoResponse);
    }

}
