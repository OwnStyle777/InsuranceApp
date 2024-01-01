package com.example.InsuranceApplication.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client updatedClient) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Nájdenie existujúceho klienta podľa ID
            Client existingClient = session.get(Client.class, updatedClient.getId());

            if (existingClient != null) {
               existingClient = updatedClient;
                session.merge(existingClient);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client getClientById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
            return null;
        }
    }
}
