package br.com.vr.mini_autorizador.domain.repository;

import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<CartaoEntity, String> {

    Optional<CartaoEntity> findByNumeroCartao(String cardNumber);

}
