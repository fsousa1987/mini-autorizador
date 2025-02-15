package br.com.vr.mini_autorizador.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CartaoRequest(@NotBlank String senha, @NotBlank String numeroCartao) {
}
