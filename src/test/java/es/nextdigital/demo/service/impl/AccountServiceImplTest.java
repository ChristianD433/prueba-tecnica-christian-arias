package es.nextdigital.demo.service.impl;

import es.nextdigital.demo.entity.ATM;
import es.nextdigital.demo.entity.Account;
import es.nextdigital.demo.entity.Bank;
import es.nextdigital.demo.entity.Customer;
import es.nextdigital.demo.entity.Transaction;
import es.nextdigital.demo.entity.TransactionType;
import es.nextdigital.demo.repository.ATMRepository;
import es.nextdigital.demo.repository.AccountRepository;
import es.nextdigital.demo.repository.CardRepository;
import es.nextdigital.demo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ATMRepository atmRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void getAccountMovements_shouldReturnPage_whenAccountExists() {
        // given
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);

        Transaction tx = new Transaction();
        tx.setId(10L);
        tx.setAmount(BigDecimal.valueOf(100));
        tx.setTimestamp(LocalDateTime.now());
        tx.setType(TransactionType.DEPOSIT);

        Page<Transaction> page = new PageImpl<>(List.of(tx));

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(transactionRepository.findByAccountId(eq(accountId), any(Pageable.class))).thenReturn(page);

        // when
        Page<Transaction> result = accountService.getAccountMovements(accountId, 0, 10);

        // then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    void getAccountMovements_shouldThrow404_whenAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> accountService.getAccountMovements(1L, 0, 10));
    }

    @Test
    void deposit_shouldIncreaseBalance_whenSameBankATM() {
        // given
        Long accountId = 1L;
        Long atmId = 1L;
        Long cardId = 1L;
        double amount = 200.0;

        Bank bank = new Bank();
        bank.setId(100L);

        Customer customer = new Customer();
        customer.setId(50L);
        customer.setBank(bank);

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(BigDecimal.valueOf(1000));
        account.setCustomer(customer);

        ATM atm = new ATM();
        atm.setId(atmId);
        atm.setBank(bank);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(atmRepository.findById(atmId)).thenReturn(Optional.of(atm));

        // when
        Double newBalance = accountService.deposit(accountId, cardId, atmId, amount);

        // then
        assertThat(newBalance).isEqualTo(1200.0);
        verify(accountRepository).saveAndFlush(any(Account.class));
        verify(transactionRepository).saveAndFlush(any(Transaction.class));
    }

    @Test
    void deposit_shouldThrow403_whenATMFromDifferentBank() {
        Long accountId = 1L;
        Long atmId = 1L;
        Long cardId = 1L;

        Bank bank1 = new Bank();
        bank1.setId(100L);

        Bank bank2 = new Bank();
        bank2.setId(200L);

        Customer customer = new Customer();
        customer.setId(50L);
        customer.setBank(bank1);

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(BigDecimal.valueOf(1000));
        account.setCustomer(customer);

        ATM atm = new ATM();
        atm.setId(atmId);
        atm.setBank(bank2);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(atmRepository.findById(atmId)).thenReturn(Optional.of(atm));

        assertThrows(ResponseStatusException.class, () -> accountService.deposit(accountId, cardId, atmId, 100.0));
    }

    @Test
    void deposit_shouldThrow404_whenAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> accountService.deposit(1L, 1L, 1L, 100.0));
    }

    @Test
    void deposit_shouldThrow404_whenATMNotFound() {
        Bank bank = new Bank();
        bank.setId(100L);

        Customer customer = new Customer();
        customer.setBank(bank);

        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(500));
        account.setCustomer(customer);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(atmRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> accountService.deposit(1L, 1L, 99L, 50.0));
    }
}
