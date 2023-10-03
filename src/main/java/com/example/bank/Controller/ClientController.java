package com.example.bank.Controller;

import com.example.bank.model.Client;
import com.example.bank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ClientController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    static final String REST_URL = "/api/profile";

    @Autowired
    protected ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> register(@RequestBody Client client) {
        if (client.getId() != null) {
            ResponseEntity.status(HttpStatus.CONFLICT);
        }
        Client created = clientRepository.save(client);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        clientRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Client get(@PathVariable int id){
        return clientRepository.get(id);
    }



}
