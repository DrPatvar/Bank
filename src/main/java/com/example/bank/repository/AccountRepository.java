package com.example.bank.repository;

import com.example.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> getAllByClient_Id(int clientId);

    @Query("SELECT a FROM Account a WHERE a.id = :id AND a.client.id = :clientId")
    Account  get(int id, int clientId);
}
