package br.com.vr.mini_autorizador.domain.service;

import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartaoService {

    CartaoResponse criarCartao(CartaoRequest cartaoRequest);

    BigDecimal verificarSaldo(String numeroCartao);

    Optional<CartaoResponse> verificarSeCartaoExiste(CartaoRequest cartaoRequest);

}
