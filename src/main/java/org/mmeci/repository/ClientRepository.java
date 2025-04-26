package org.mmeci.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import org.mmeci.entity.Client;
import java.util.List;


@AllArgsConstructor
public class ClientRepository {
    private final EntityManager entityManager;

    public void addClient(Client client){
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(client);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }

    }

    public void deleteClientyId(Long id) {
        EntityTransaction transaction= entityManager.getTransaction();
        try {
            transaction.begin();
            Client client = entityManager.find(Client.class, id);
            if (client != null) {

                entityManager.remove(client);
                transaction.commit();

            }

        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public Client getClientById(Long id){
        return entityManager.find(Client.class, id);
    }

    public List<Client> getAllClients(){
        return entityManager.createQuery("SELECT b FROM clients b", Client.class)
                .getResultList();
    }



}