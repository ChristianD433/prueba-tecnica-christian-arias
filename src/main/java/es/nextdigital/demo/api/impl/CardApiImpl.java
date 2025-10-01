package es.nextdigital.demo.api.impl;

import es.nextdigital.api.CardApi;
import es.nextdigital.dto.CardStatusResponseDto;
import es.nextdigital.dto.PinChangeRequestDto;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardApiImpl implements CardApi {
    @Override
    public ResponseEntity<CardStatusResponseDto> cardsCardIdActivatePost(String X_ATM_CODE, String X_CARD_ID, String cardId, PinChangeRequestDto pinChangeRequestDto) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<Void> cardsCardIdPinPost(String X_ATM_CODE, String X_CARD_ID, String cardId, PinChangeRequestDto pinChangeRequestDto) {
        throw new NotImplementedException();
    }
}
