package com.example.bank.service;

import com.example.bank.error.IllegalRequestDataException;
import com.example.bank.util.SecurityUtil;
import com.example.bank.model.Account;
import com.example.bank.model.Client;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account get(int id) {
        int authClientId = SecurityUtil.authClientId();
        Account account = accountRepository.get(id, authClientId);
        if(account == null){
          throw new IllegalRequestDataException("This is not your account");
        }
        return account;
    }

    public void delete(int id) {
        int authClientId = SecurityUtil.authClientId();
        if (accountRepository.get(id, authClientId) == null){
                throw new IllegalRequestDataException("This is not your account");
        }
        accountRepository.deleteById(id);
    }

    public Account save(Account account) {
        Client client = new Client(SecurityUtil.authClientId());
        account.setClient(client);
        return accountRepository.save(account);
    }

    public List<Account> getAll() {
        int authClientId = SecurityUtil.authClientId();
        return accountRepository.getAllByClient_Id(authClientId);
    }
}
