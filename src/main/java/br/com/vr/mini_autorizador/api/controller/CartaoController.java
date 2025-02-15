package br.com.vr.mini_autorizador.api.controller;

import br.com.vr.mini_autorizador.domain.dto.request.CartaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.CartaoResponse;
import br.com.vr.mini_autorizador.domain.dto.response.SaldoResponse;
import br.com.vr.mini_autorizador.domain.service.CartaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<CartaoResponse> criarCartao(@RequestBody @Valid CartaoRequest cartaoRequest) {
        return cartaoService.verificarSeCartaoExiste(cartaoRequest)
                .map(cartao -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(cartao))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(cartaoService.criarCartao(cartaoRequest)));
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<SaldoResponse> verificarSaldo(@PathVariable String numeroCartao) {
        return cartaoService.verificarSaldo(numeroCartao)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
