package es.nextdigital.demo.api.impl;

import es.nextdigital.api.AccountApi;
import es.nextdigital.demo.entity.Transaction;
import es.nextdigital.demo.mapper.TransactionMapper;
import es.nextdigital.demo.service.AccountService;
import es.nextdigital.demo.service.impl.AccountServiceImpl;
import es.nextdigital.dto.DepositRequestDto;
import es.nextdigital.dto.DepositResponseDto;
import es.nextdigital.dto.MovementsResponseDto;
import es.nextdigital.dto.TransferRequestDto;
import es.nextdigital.dto.TransferResponseDto;
import es.nextdigital.dto.WithdrawRequestDto;
import es.nextdigital.dto.WithdrawResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountApiImpl implements AccountApi {

    private final AccountService accountService;

    private final TransactionMapper transactionMapper;

    @Override
    public ResponseEntity<DepositResponseDto> accountsAccountIdDepositPost(String X_ATM_CODE, String X_CARD_ID,
                                                                           Long accountId, DepositRequestDto depositRequestDto) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<MovementsResponseDto> accountsAccountIdMovementsGet(String X_ATM_CODE, String X_CARD_ID, 
                                                                              Long accountId, Integer page, Integer size) {

        Page<Transaction> transactions = accountService.getAccountMovements(accountId, page, size);

        return ResponseEntity.ok(transactionMapper.toMovementsResponse(transactions));
    }

    @Override
    public ResponseEntity<TransferResponseDto> accountsAccountIdTransferPost(String X_ATM_CODE, String X_CARD_ID,
                                                                             Long accountId, TransferRequestDto transferRequestDto) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<WithdrawResponseDto> accountsAccountIdWithdrawPost(String X_ATM_CODE, String X_CARD_ID,
                                                                             Long accountId, WithdrawRequestDto withdrawRequestDto) {
        throw new NotImplementedException();
    }
}
