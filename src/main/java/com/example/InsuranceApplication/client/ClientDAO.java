package com.example.InsuranceApplication.client;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean saveClient(Client client, Session session) {
        try {
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean saveClientImage(ClientProfilePicture clientProfilePicture, Session session) {
        try {
            session.beginTransaction();
            session.persist(clientProfilePicture);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateClientImage(ClientProfilePicture updatedClientPicture) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
                // find existing client
                ClientProfilePicture existingClientPicture = getClientPictureById(updatedClientPicture.getImageId());
            System.out.println("EXISTING IMAGE " + existingClientPicture);
            System.out.println("UPDATED IMAGE" + updatedClientPicture);
                if (existingClientPicture != null) {
                    // Update existing profile picture with new data
                 existingClientPicture = updatedClientPicture;
                    // Merge is not necessary here since the object is already managed
                    session.merge(existingClientPicture);
                }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    public ClientProfilePicture getClientPictureById(Long imageId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ClientProfilePicture> criteriaQuery = builder.createQuery(ClientProfilePicture.class);
            Root<ClientProfilePicture> root = criteriaQuery.from(ClientProfilePicture.class);
            criteriaQuery.select(root).where(builder.equal(root.get("imageId"), imageId));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateClient(Client updatedClient) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // find existing client
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
