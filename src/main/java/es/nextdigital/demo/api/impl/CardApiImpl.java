package es.nextdigital.demo.api.impl;

import es.nextdigital.api.CardApi;
import es.nextdigital.demo.entity.Card;
import es.nextdigital.demo.mapper.CardMapper;
import es.nextdigital.demo.service.CardService;
import es.nextdigital.dto.CardStatusResponseDto;
import es.nextdigital.dto.PinChangeRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardApiImpl implements CardApi {

    private final CardService cardService;

    private final CardMapper cardMapper;

    @Override
    public ResponseEntity<CardStatusResponseDto> cardsCardIdActivatePost(Long X_ATM_CODE, Long X_CARD_ID,
                                                                         Long cardId) {
      Card card = cardService.activateCard(cardId);

      return ResponseEntity.ok(cardMapper.toDto(card));
    }

    @Override
    public ResponseEntity<Void> cardsCardIdPinPost(Long X_ATM_CODE, Long X_CARD_ID, Long cardId, PinChangeRequestDto pinChangeRequestDto) {
        throw new NotImplementedException();
    }
}
