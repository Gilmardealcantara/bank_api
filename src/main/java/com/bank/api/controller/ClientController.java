package com.bank.api.controller;

import com.bank.api.exception.ResourceNotFoundException;
import com.bank.api.model.Client;
import com.bank.api.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    // Get All Clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Create a new Client
    @PostMapping("/client")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Get a Single Client
    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable(value = "id") Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
    }

    // Update a Client
    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable(value = "id") Long clientId,
                                            @Valid @RequestBody Client clientDetails) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

        client.setName(clientDetails.getName());
        /*TODO ...*/
        
        Client updatedClient = clientRepository.save(client);
        return updatedClient;
    }

    // Delete a Client
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

        clientRepository.delete(client);

        return ResponseEntity.ok().build();
    }

}
