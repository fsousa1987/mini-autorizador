package br.com.vr.mini_autorizador.domain.dto.response;

import lombok.Builder;

@Builder
public record CartaoResponse(String senha, String numeroCartao) {
}
