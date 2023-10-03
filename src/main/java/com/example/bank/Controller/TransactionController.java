package com.example.bank.Controller;

import com.example.bank.Util.SecurityUtil;
import com.example.bank.error.IllegalRequestDataException;
import com.example.bank.model.Account;
import com.example.bank.model.Client;
import com.example.bank.model.Operation;
import com.example.bank.model.Transaction;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.ClientRepository;
import com.example.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = TransactionController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
     static final String REST_URL = "/api/transaction";

     @Autowired
     private TransactionRepository transactionRepository;

     @Autowired
     private ClientRepository clientRepository;

     @Autowired
     private AccountRepository accountRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Transaction> register(@RequestBody Transaction transaction) {
        Integer clientCode = SecurityUtil.authClientPassCode();
        Integer clientDbCode = clientRepository.get(SecurityUtil.authClientId()).getPassCode();
        if (!clientCode.equals(clientDbCode)){
            throw new IllegalRequestDataException("pin code is not correct");
        }
        Account accountFirst =  accountRepository.get(transaction.getAccountId1());
        Double balanceFirst = accountFirst.getBalance();
        Double amount = transaction.getAmount();
        if (transaction.getId() != null) {
            throw new IllegalRequestDataException("the transaction exists");
        }

        switch (transaction.getName()){
            case DEPOSIT -> {
                balanceFirst+= amount;
                accountFirst.setBalance(balanceFirst);
                accountRepository.save(accountFirst);
                transaction.setName(Operation.DEPOSIT);
            }


            case WITHDRAW -> {
                if (balanceFirst < amount) {
                    throw new IllegalRequestDataException("there are not enough funds in the account");
                }
                balanceFirst-= amount;
                accountFirst.setBalance(balanceFirst);
                accountRepository.save(accountFirst);
                transaction.setName(Operation.WITHDRAW);
            }
            case TRANSFER ->{
                Account accountSecond = accountRepository.get(transaction.getAccountId2());
                Double balanceSecond = accountSecond.getBalance();
                if (balanceFirst < amount) {
                    throw new IllegalRequestDataException("there are not enough funds in the account");
                }
                balanceFirst -= amount;
                balanceSecond += amount;
                accountFirst.setBalance(balanceFirst);
                accountRepository.save(accountFirst);
                accountSecond.setBalance(balanceSecond);
                accountRepository.save(accountSecond);
            }
        }
        Transaction created = transactionRepository.save(transaction);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(transaction);
    }


}
