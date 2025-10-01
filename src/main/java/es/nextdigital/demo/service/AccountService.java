package es.nextdigital.demo.service;

import es.nextdigital.demo.entity.Transaction;
import org.springframework.data.domain.Page;

public interface AccountService {
    Page<Transaction> getAccountMovements(Long accountId, Integer page, Integer size);

    Double deposit(Long accountId, Long xCardId, Long xAtmCode, Double amount);
}
