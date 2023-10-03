package com.example.bank.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private Operation name;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "account_id1")
    private Integer accountId1;

    @Column(name = "account_id2")
    private Integer accountId2;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime date = LocalDateTime.now();

}
