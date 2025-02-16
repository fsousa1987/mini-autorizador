package br.com.vr.mini_autorizador.domain.repository;

import br.com.vr.mini_autorizador.domain.model.CartaoEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<CartaoEntity, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CartaoEntity> findByNumeroCartao(String cardNumber);

}
