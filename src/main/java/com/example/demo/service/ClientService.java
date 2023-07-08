package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Loan;
import com.example.demo.model.Product;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public void updateByName(String name) {
        Client client = clientRepository.findByName(name);

        clientRepository.deleteAllLoansForClient(client.getDecisionId());
        clientRepository.deleteAllProductsForClient(client.getDecisionId());

        System.out.println(client);

        client.getLoans().addAll(List.of(
                Loan.builder().amount(123L).build(),
                Loan.builder().amount(50L).build()));
        client.getProducts().addAll(List.of(
                Product.builder().name("ovd_card").build(),
                Product.builder().name("cr_card").build()));

        client.setAge(23);

        clientRepository.save(client);
    }
}
