package es.nextdigital.demo.api.impl;

import es.nextdigital.api.AccountApi;
import es.nextdigital.demo.entity.Transaction;
import es.nextdigital.demo.mapper.TransactionMapper;
import es.nextdigital.demo.service.AccountService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class AccountApiImpl implements AccountApi {

    private final AccountService accountService;

    private final TransactionMapper transactionMapper;

    @Override
    public ResponseEntity<DepositResponseDto> accountsAccountIdDepositPost(Long X_ATM_CODE, Long X_CARD_ID,
                                                                           Long accountId, DepositRequestDto depositRequestDto) {
        if (depositRequestDto.getAmount() == null || depositRequestDto.getAmount() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El monto debe ser positivo");
        }

        return ResponseEntity.ok(
                new DepositResponseDto(accountService.deposit(accountId,X_CARD_ID, X_ATM_CODE, depositRequestDto.getAmount())));
    }

    @Override
    public ResponseEntity<MovementsResponseDto> accountsAccountIdMovementsGet(Long X_ATM_CODE, Long X_CARD_ID,
                                                                              Long accountId, Integer page, Integer size) {

        Page<Transaction> transactions = accountService.getAccountMovements(accountId, page, size);

        return ResponseEntity.ok(transactionMapper.toMovementsResponse(transactions));
    }

    @Override
    public ResponseEntity<TransferResponseDto> accountsAccountIdTransferPost(Long X_ATM_CODE, Long X_CARD_ID,
                                                                             Long accountId, TransferRequestDto transferRequestDto) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<WithdrawResponseDto> accountsAccountIdWithdrawPost(Long X_ATM_CODE, Long X_CARD_ID,
                                                                             Long accountId, WithdrawRequestDto withdrawRequestDto) {
        throw new NotImplementedException();
    }
}
