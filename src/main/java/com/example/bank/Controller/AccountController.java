package com.example.bank.Controller;

import com.example.bank.Util.SecurityUtil;
import com.example.bank.model.Account;
import com.example.bank.model.Client;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AccountController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    static final String REST_URL = "/api/account";
    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id) {
        return accountService.get(id);
    }

    @GetMapping()
    public List<Account> getAllAccountClient() {
        return accountService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable int id) {
        accountService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAccount(@RequestBody Account account, @PathVariable int id) {
        accountService.update(account, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Account> register(@RequestBody Account account) {
        Client client = new Client(SecurityUtil.authClientId());
        if (account.getId() != null) {
            ResponseEntity.status(HttpStatus.CONFLICT);
        }
        account.setClient(client);
        Account created = accountService.save(account);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
