package es.nextdigital.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
