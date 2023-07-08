package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Loan> {

    Client findByName(String name);

    @Modifying(flushAutomatically = true)
    @Query("delete Product p where p.decisionId = :decisionId")
    void deleteAllProductsForClient(Long decisionId);

    @Modifying(flushAutomatically = true)
    @Query("delete Loan l where l.decisionId = :decisionId")
    void deleteAllLoansForClient(Long decisionId);
}
