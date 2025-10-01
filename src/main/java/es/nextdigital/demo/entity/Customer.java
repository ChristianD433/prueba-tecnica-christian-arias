package es.nextdigital.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import jakarta.persistence.*;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
}

