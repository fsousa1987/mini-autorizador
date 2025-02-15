package br.com.vr.mini_autorizador.domain.service.impl;

import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.CartaoJaExistenteException;
import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import br.com.vr.mini_autorizador.domain.repository.CartaoRepository;
import br.com.vr.mini_autorizador.domain.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static br.com.vr.mini_autorizador.core.mapper.Mapper.transformarCartaoEntityEmCartaoResponse;
import static br.com.vr.mini_autorizador.core.mapper.Mapper.transformarCartaoRequestEmCartaoEntity;

@Service
@RequiredArgsConstructor
public class CartaoServiceImpl implements CartaoService {

    private final CartaoRepository cartaoRepository;

    @Override
    public CartaoResponse criarCartao(CartaoRequest cartaoRequest) {
        verificarSeCartaoExiste(cartaoRequest.numeroCartao());
        CartaoEntity cartaoEntity = transformarCartaoRequestEmCartaoEntity(cartaoRequest);

        cartaoEntity = cartaoRepository.save(cartaoEntity);

        return transformarCartaoEntityEmCartaoResponse(cartaoEntity);
    }

    @Override
    public BigDecimal verificarSaldo(String cardNumber) {
        return null;
    }

    private void verificarSeCartaoExiste(String cardNumber) {
        cartaoRepository
                .findById(cardNumber)
                .ifPresent(cartaoEntity -> {
                    throw new CartaoJaExistenteException("Cartão com o número [%s] já existe".formatted(cardNumber));
                });
    }

}
