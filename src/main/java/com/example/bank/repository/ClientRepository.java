package com.example.bank.repository;

import com.example.bank.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ClientRepository  extends JpaRepository<Client, BigInteger> {

}
