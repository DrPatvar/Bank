package com.example.bank.service;

import com.example.bank.Util.SecurityUtil;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account get(int id){
        int authClientId = SecurityUtil.authClientId();
    return accountRepository.getAccountByIdAndClient_Id(id, authClientId);
    }

    public void delete(int id) {
        int authClientId = SecurityUtil.authClientId();
        accountRepository.deleteByIdAndClient_Id(id, authClientId);
    }

    public Account save(Account account) {
        int authClientId = SecurityUtil.authClientId();
        return accountRepository.save(account);
    }

    public List<Account> getAll() {
        int authClientId = SecurityUtil.authClientId();
        return accountRepository.getAllByClient_Id(authClientId);
    }

    public void update(Account account, int id) {
        int authClientId = SecurityUtil.authClientId();
        accountRepository.save(account);
    }
}
