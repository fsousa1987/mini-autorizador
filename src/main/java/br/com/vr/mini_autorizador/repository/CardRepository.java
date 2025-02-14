package br.com.vr.mini_autorizador.repository;

import br.com.vr.mini_autorizador.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, String> {

    Optional<Card> findByCardNumber(String cardNumber);

}
