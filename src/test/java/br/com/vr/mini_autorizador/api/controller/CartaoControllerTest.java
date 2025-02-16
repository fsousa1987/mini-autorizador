package br.com.vr.mini_autorizador.api.controller;

import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.dto.response.SaldoResponse;
import br.com.vr.mini_autorizador.domain.service.CartaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.vr.mini_autorizador.factory.CartaoFactory.criarUmCartaoRequest;
import static br.com.vr.mini_autorizador.factory.CartaoFactory.criarUmCartaoResponse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartaoControllerTest {

    private AutoCloseable openMocks;

    private MockMvc mockMvc;

    @Mock
    private CartaoService cartaoService;

    @InjectMocks
    private CartaoController cartaoController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartaoController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveCriarCartaoComSucesso() throws Exception {
        CartaoRequest request = criarUmCartaoRequest();
        CartaoResponse response = criarUmCartaoResponse();

        when(cartaoService.verificarSeCartaoExiste(request)).thenReturn(Optional.empty());
        when(cartaoService.criarCartao(request)).thenReturn(response);

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCartao").value("6549873025634501"))
                .andExpect(jsonPath("$.senha").value("1234"));
    }

    @Test
    void deveRetornar422QuandoCartaoJaExiste() throws Exception {
        CartaoRequest request = criarUmCartaoRequest();
        CartaoResponse response = criarUmCartaoResponse();

        when(cartaoService.verificarSeCartaoExiste(request)).thenReturn(Optional.of(response));

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.numeroCartao").value("6549873025634501"))
                .andExpect(jsonPath("$.senha").value("1234"));
    }

    @Test
    void deveRetornarSaldoQuandoCartaoExiste() throws Exception {
        String numeroCartao = "6549873025634501";
        SaldoResponse saldoResponse = new SaldoResponse(BigDecimal.valueOf(300));

        when(cartaoService.verificarSaldo(numeroCartao)).thenReturn(Optional.of(saldoResponse));

        mockMvc.perform(get("/cartoes/{numeroCartao}", numeroCartao)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saldo").value(300));
    }

    @Test
    void deveRetornar404_QuandoCartaoNaoExiste() throws Exception {
        String numeroCartao = "9999999999999999";

        when(cartaoService.verificarSaldo(numeroCartao)).thenReturn(Optional.empty());

        mockMvc.perform(get("/cartoes/{numeroCartao}", numeroCartao)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
