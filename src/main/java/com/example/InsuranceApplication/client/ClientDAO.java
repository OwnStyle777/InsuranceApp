package com.example.InsuranceApplication.client;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

    public boolean saveClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public Client getClientByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
            Root<Client> root = criteriaQuery.from(Client.class);
            criteriaQuery.select(root).where(builder.equal(root.get("loginInfo").get("email"), email));

            Query query = session.createQuery(criteriaQuery);
            return (Client) query.getSingleResult();
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEmailInDatabase(String email) {

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<Client> root = criteriaQuery.from(Client.class);
            criteriaQuery.select(builder.count(root)).where(builder.equal(root.get("loginInfo").get("email"), email));

            Query query = session.createQuery(criteriaQuery);
            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
