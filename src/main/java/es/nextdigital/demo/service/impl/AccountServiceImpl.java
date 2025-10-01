package es.nextdigital.demo.service.impl;

import es.nextdigital.demo.entity.ATM;
import es.nextdigital.demo.entity.Account;
import es.nextdigital.demo.entity.Transaction;
import es.nextdigital.demo.entity.TransactionType;
import es.nextdigital.demo.repository.ATMRepository;
import es.nextdigital.demo.repository.AccountRepository;
import es.nextdigital.demo.repository.CardRepository;
import es.nextdigital.demo.repository.TransactionRepository;
import es.nextdigital.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final CardRepository cardRepository;

    private final ATMRepository atmRepository;

    @Override
    public Page<Transaction> getAccountMovements(Long accountId, Integer page, Integer size) {

        accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return transactionRepository.findByAccountId(accountId, pageable);
    }

    @Override
    public Double deposit(Long accountId, Long xCardId, Long xAtmCode, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        ATM atm = atmRepository.findById(xAtmCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ATM not found"));

        if(!Objects.equals(atm.getBank().getId(), account.getCustomer().getBank().getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "ATM bank does not match account bank");
        }

        account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
        accountRepository.saveAndFlush(account);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(BigDecimal.valueOf(amount));
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setAccount(account);
        transactionRepository.saveAndFlush(transaction);

        return account.getBalance().doubleValue();
    }
}
