package com.example.bank.repository;

import com.example.bank.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @EntityGraph(attributePaths = {"accounts"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "SELECT c FROM Client c")
    List<Client> getAll();

    @Transactional
    @Query("SELECT c FROM Client c JOIN FETCH c.accounts WHERE c.id = :id")
    Client get(int id);

}
