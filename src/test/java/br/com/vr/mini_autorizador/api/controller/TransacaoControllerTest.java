package br.com.vr.mini_autorizador.api.controller;

import br.com.vr.mini_autorizador.domain.dto.request.TransacaoRequest;
import br.com.vr.mini_autorizador.domain.service.TransacaoService;
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

import static br.com.vr.mini_autorizador.factory.TransacaoFactory.criarUmaTransacaoRequest;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransacaoControllerTest {

    private AutoCloseable openMocks;

    private MockMvc mockMvc;

    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private TransacaoController transacaoController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRealizarTransacaoComSucesso() throws Exception {
        TransacaoRequest request = criarUmaTransacaoRequest();

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.detalhe").value("OK"));

        verify(transacaoService, times(1)).processarTransacao(request);
    }

}
