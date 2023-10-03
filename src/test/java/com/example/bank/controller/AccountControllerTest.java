package com.example.bank.controller;

import com.example.bank.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.bank.controller.TestUtil.ACCOUNT_ID;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AccountController.REST_URL;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void getAccount() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + '/' + ACCOUNT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllAccountClient() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void deleteAccount() throws Exception {
        int expected = accountRepository.findAll().size();
        perform(MockMvcRequestBuilders.delete(REST_URL + '/' + ACCOUNT_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assertions.assertNotEquals(expected, accountRepository.findAll().size());
    }
}