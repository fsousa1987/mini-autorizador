package br.com.vr.mini_autorizador.domain.service;

import br.com.vr.mini_autorizador.domain.dto.request.TransacaoRequest;

public interface TransacaoService {

    void processarTransacao(TransacaoRequest transacaoRequest);

}
