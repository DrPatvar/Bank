package com.example.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;


@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = {"passCode"})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer passCode;
    private BigInteger balance;

}
