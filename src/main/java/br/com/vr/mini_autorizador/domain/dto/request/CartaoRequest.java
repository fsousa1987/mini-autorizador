package br.com.vr.mini_autorizador.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CartaoRequest(@NotBlank(message = "O campo senha é obrigatório") String senha,
                            @NotBlank(message = "O campo número do cartão é obrigatório") String numeroCartao) {
}
