package es.nextdigital.demo.service.impl;

import es.nextdigital.demo.entity.Card;
import es.nextdigital.demo.repository.CardRepository;
import es.nextdigital.demo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public Card activateCard(Long cardId) {
        return cardRepository.findById(cardId)
                .map(card -> {
                    card.setActive(true);
                    return cardRepository.saveAndFlush(card);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }
}
