package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Loan;
import com.example.demo.model.Product;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public void updateByName(String name) {
        Client client = clientRepository.findByName(name);
        System.out.println(client);

        client.getLoans().removeIf(loan -> loan.getId().equals(1L));
        client.getProducts().removeIf(product -> product.getName().equals("cr_card"));

        client.getLoans().add(Loan.builder().amount(123L).build());
        client.getProducts().add(Product.builder().name("ovd_card").build());

        clientRepository.save(client);
    }
}
