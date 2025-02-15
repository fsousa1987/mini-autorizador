package br.com.vr.mini_autorizador.api.controller;

import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.service.CartaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<CartaoResponse> createCard(@RequestBody @Valid CartaoRequest cartaoRequest) {
        CartaoResponse cartaoResponse = cartaoService.criarCartao(cartaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoResponse);
    }

}
