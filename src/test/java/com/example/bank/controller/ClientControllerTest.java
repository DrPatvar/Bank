package com.example.bank.controller;

import com.example.bank.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.bank.controller.TestUtil.CLIENT_ID;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ClientController.REST_URL;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + '/' + CLIENT_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assertions.assertFalse(clientRepository.findById(CLIENT_ID).isPresent());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + '/' + CLIENT_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }
}