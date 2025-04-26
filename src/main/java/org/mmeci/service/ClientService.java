package org.mmeci.service;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.mmeci.entity.Client;
import org.mmeci.repository.ClientRepository;

import java.util.List;


@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(EntityManager entityManager){
        this.clientRepository = new ClientRepository(entityManager);
    }

    public void addClient(String name, String lastname, String email){
        Client client = new Client(name, lastname, email);
        clientRepository.addClient(client);
    }

    public void deleteClientById(Long movieId) {
        clientRepository.deleteClientyId(movieId);

    }


    public Client getClientById(Long id){
        return clientRepository.getClientById(id);
    }

    public List<Client> getAllClients(){
        return clientRepository.getAllClients();
    }
}