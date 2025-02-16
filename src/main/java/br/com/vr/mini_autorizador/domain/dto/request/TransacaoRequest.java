package br.com.vr.mini_autorizador.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransacaoRequest(
        @NotBlank String numeroCartao,
        @NotBlank String senhaCartao,
        @NotNull @Min(0) BigDecimal valor
) {
}
