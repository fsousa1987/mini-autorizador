package br.com.vr.mini_autorizador.domain.service;

import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.CartaoNaoEncontradoException;
import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.SaldoInsuficienteException;
import br.com.vr.mini_autorizador.api.exceptionhandler.exceptions.SenhaInvalidaException;
import br.com.vr.mini_autorizador.domain.dto.request.TransacaoRequest;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import br.com.vr.mini_autorizador.domain.repository.CartaoRepository;
import br.com.vr.mini_autorizador.domain.service.impl.TransacaoServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.vr.mini_autorizador.factory.CartaoFactory.criarUmCartaoEntity;
import static br.com.vr.mini_autorizador.factory.TransacaoFactory.criarUmaTransacaoRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransacaoServiceImplTest {

    private AutoCloseable openMocks;

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private TransacaoServiceImpl transacaoService;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveProcessarTransacaoComSucesso() {
        TransacaoRequest request = new TransacaoRequest("1234567890123456", "1234", BigDecimal.valueOf(100));
        CartaoEntity cartao = criarUmCartaoEntity();

        when(cartaoRepository.findByNumeroCartao(request.numeroCartao())).thenReturn(Optional.of(cartao));

        transacaoService.processarTransacao(request);

        assertEquals(BigDecimal.valueOf(400), cartao.getSaldo()); // ✅ O saldo foi debitado corretamente
        verify(cartaoRepository, times(1)).save(cartao);
    }

    @Test
    void deveLancarExcecaoQuandoCartaoNaoExiste() {
        TransacaoRequest request = criarUmaTransacaoRequest();

        when(cartaoRepository.findByNumeroCartao(request.numeroCartao())).thenReturn(Optional.empty());

        assertThrows(CartaoNaoEncontradoException.class, () -> transacaoService.processarTransacao(request));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaInvalida() {
        TransacaoRequest request = new TransacaoRequest("1234567890123456", "0000", BigDecimal.valueOf(100));
        CartaoEntity cartao = criarUmCartaoEntity();

        when(cartaoRepository.findByNumeroCartao(request.numeroCartao())).thenReturn(Optional.of(cartao));

        assertThrows(SenhaInvalidaException.class, () -> transacaoService.processarTransacao(request));
    }

    @Test
    void deveLancarExcecao_QuandoSaldoInsuficiente() {
        TransacaoRequest request = criarUmaTransacaoRequest();
        CartaoEntity cartao = criarUmCartaoEntity();

        when(cartaoRepository.findByNumeroCartao(request.numeroCartao())).thenReturn(Optional.of(cartao));

        assertThrows(SaldoInsuficienteException.class, () -> transacaoService.processarTransacao(request));
    }

}
