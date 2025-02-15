package br.com.vr.mini_autorizador.api.controller;

import br.com.vr.mini_autorizador.domain.dto.request.TransacaoRequest;
import br.com.vr.mini_autorizador.domain.dto.response.TransacaoResponse;
import br.com.vr.mini_autorizador.domain.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> realizarTransacao(@RequestBody @Valid TransacaoRequest transacaoRequest) {
        transacaoService.processarTransacao(transacaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TransacaoResponse("OK"));
    }

}
