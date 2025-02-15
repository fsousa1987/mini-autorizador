package br.com.vr.mini_autorizador.domain.service.impl;

import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.CartaoNaoEncontradoException;
import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.SaldoInsuficienteException;
import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.SenhaInvalidaException;
import br.com.vr.mini_autorizador.domain.dto.request.TransacaoRequest;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import br.com.vr.mini_autorizador.domain.repository.CartaoRepository;
import br.com.vr.mini_autorizador.domain.service.TransacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.vr.mini_autorizador.domain.enums.ErroMensagem.*;

@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private final CartaoRepository cartaoRepository;

    @Override
    @Transactional
    public void processarTransacao(TransacaoRequest transacaoRequest) {
        CartaoEntity cartaoEncontrado = verificarSeCartaoExiste(transacaoRequest);
        validarSenhaDoCartao(transacaoRequest.senhaCartao(), cartaoEncontrado.getSenha());
        validarSaldoNoCartao(cartaoEncontrado.getSaldo(), transacaoRequest.valor());

        cartaoEncontrado.setSaldo(cartaoEncontrado.getSaldo().subtract(transacaoRequest.valor()));
        cartaoRepository.save(cartaoEncontrado);
    }

    private void validarSaldoNoCartao(BigDecimal saldo, BigDecimal valorTransacao) {
        Optional.ofNullable(saldo)
                .filter(s -> s.compareTo(valorTransacao) >= 0)
                .orElseThrow(() -> new SaldoInsuficienteException(SALDO_INSUFICIENTE.getMensagem()));
    }

    private CartaoEntity verificarSeCartaoExiste(TransacaoRequest transacaoRequest) {
        return cartaoRepository
                .findByNumeroCartao(transacaoRequest.numeroCartao())
                .orElseThrow(() -> new CartaoNaoEncontradoException(CARTAO_INEXISTENTE.getMensagem()));
    }

    private void validarSenhaDoCartao(@NotBlank String senhaRequest, String senhaCartao) {
        Optional.ofNullable(senhaCartao)
                .filter(senha -> senha.equals(senhaRequest))
                .orElseThrow(() -> new SenhaInvalidaException(SENHA_INVALIDA.getMensagem()));
    }

}
