package com.example.bank.repository;

import com.example.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    void deleteByIdAndClient_Id(int id, int clientId);
    Account getAccountByIdAndClient_Id(int id, int clientId);
    List<Account> getAllByClient_Id(int clientId);
}
