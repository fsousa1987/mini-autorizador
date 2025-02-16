package br.com.vr.mini_autorizador.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table(name = "cartao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoEntity {

    @Id
    private String numeroCartao;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private BigDecimal saldo;

    @PrePersist
    public void inicializarSaldo() {
        saldo = Optional.ofNullable(saldo).orElse(BigDecimal.valueOf(500.00));
    }

}

