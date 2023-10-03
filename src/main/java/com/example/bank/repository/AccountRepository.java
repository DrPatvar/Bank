package com.example.bank.repository;

import com.example.bank.model.Account;
import com.example.bank.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    void deleteByIdAndClient_Id(int id, int clientId);
    Account getAccountByIdAndClient_Id(int id, int clientId);
    List<Account> getAllByClient_Id(int clientId);

    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Account get(int id);
}
