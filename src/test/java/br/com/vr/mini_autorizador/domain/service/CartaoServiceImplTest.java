package br.com.vr.mini_autorizador.domain.service;

import br.com.vr.mini_autorizador.core.mapper.Mapper;
import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.dto.response.SaldoResponse;
import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import br.com.vr.mini_autorizador.domain.repository.CartaoRepository;
import br.com.vr.mini_autorizador.domain.service.impl.CartaoServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.vr.mini_autorizador.factory.CartaoFactory.criarUmCartaoEntity;
import static br.com.vr.mini_autorizador.factory.CartaoFactory.criarUmCartaoRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CartaoServiceImplTest {

    private AutoCloseable openMocks;

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoServiceImpl cartaoService;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        Mapper mapper = new Mapper();
        cartaoService = new CartaoServiceImpl(cartaoRepository, mapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveCriarCartaoComSucesso() {
        CartaoRequest request = criarUmCartaoRequest();
        CartaoEntity entity = criarUmCartaoEntity();
        when(cartaoRepository.findById(request.numeroCartao())).thenReturn(Optional.empty());
        when(cartaoRepository.save(any(CartaoEntity.class))).thenReturn(entity);

        CartaoResponse response = cartaoService.criarCartao(request);

        assertNotNull(response);
        assertEquals("1234567890123456", response.numeroCartao());
        verify(cartaoRepository, times(1)).save(any(CartaoEntity.class));
    }

    @Test
    void deveRetornarCartaoQuandoCartaoExiste() {
        CartaoRequest request = criarUmCartaoRequest();
        CartaoEntity entity = criarUmCartaoEntity();

        when(cartaoRepository.findById(request.numeroCartao())).thenReturn(Optional.of(entity));

        Optional<CartaoResponse> result = cartaoService.verificarSeCartaoExiste(request);

        assertTrue(result.isPresent());
        assertEquals("1234567890123456", result.get().numeroCartao());
    }

    @Test
    void deveRetornarSaldoCorreto() {
        CartaoEntity entity = criarUmCartaoEntity();

        when(cartaoRepository.findById("1234567890123456")).thenReturn(Optional.of(entity));

        Optional<SaldoResponse> saldoResponse = cartaoService.verificarSaldo("1234567890123456");

        saldoResponse.ifPresent(valor -> assertEquals(BigDecimal.valueOf(500), valor.saldo()));
    }

}
