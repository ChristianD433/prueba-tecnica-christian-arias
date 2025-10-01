package es.nextdigital.demo.entity;

import jakarta.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardType type; // CREDIT or DEBIT

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // getters and setters
}
