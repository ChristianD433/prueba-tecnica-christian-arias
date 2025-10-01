package es.nextdigital.demo.api.impl;

import es.nextdigital.api.AccountApi;
import es.nextdigital.dto.DepositRequestDto;
import es.nextdigital.dto.DepositResponseDto;
import es.nextdigital.dto.MovementsResponseDto;
import es.nextdigital.dto.TransferRequestDto;
import es.nextdigital.dto.TransferResponseDto;
import es.nextdigital.dto.WithdrawRequestDto;
import es.nextdigital.dto.WithdrawResponseDto;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountApiImpl implements AccountApi {

    @Override
    public ResponseEntity<DepositResponseDto> accountsAccountIdDepositPost(String X_ATM_CODE, String X_CARD_ID, 
                                                                           String accountId, DepositRequestDto depositRequestDto) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<MovementsResponseDto> accountsAccountIdMovementsGet(String X_ATM_CODE, String X_CARD_ID, 
                                                                              String accountId, Integer page, Integer size) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<TransferResponseDto> accountsAccountIdTransferPost(String X_ATM_CODE, String X_CARD_ID, 
                                                                             String accountId, TransferRequestDto transferRequestDto) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<WithdrawResponseDto> accountsAccountIdWithdrawPost(String X_ATM_CODE, String X_CARD_ID, 
                                                                             String accountId, WithdrawRequestDto withdrawRequestDto) {
        throw new NotImplementedException();
    }
}
