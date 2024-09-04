package example.db;

import java.sql.SQLOutput;
import java.util.List;

import example.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UserDao {

    public Users getUser(String userName) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Users> cq = cb.createQuery(Users.class);
            Root<Users> userRoot = cq.from(Users.class);
            cq.select(userRoot).where(cb.equal(userRoot.get("userName"), userName));

            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void addUser(Users user) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            // Get an EntityManager instance
            em = HibernateUtil.getEntityManager();
            System.out.println("inside try");
            // Start a transaction
            tx = em.getTransaction();
            tx.begin();
            System.out.println("inside transaction");

            // Persist the user entity
            em.persist(user);
            System.out.println("persisted");

            // Commit the transaction
            tx.commit();
            System.out.println("commited");

        } catch (Exception e) {
            // Rollback the transaction in case of an error
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // Log this exception in real applications
        } finally {
            // Close the EntityManager
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Users> searchUsers(String keyword) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Users> cq = cb.createQuery(Users.class);
            Root<Users> userRoot = cq.from(Users.class);

            cq.select(userRoot).where(cb.like(userRoot.get("userName"), "%" + keyword + "%"));

            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void testConnection() {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();

        em.getTransaction().commit();
        em.close();

    }

    public List<Users> getAllUsers(String userName) {
        EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> cq = cb.createQuery(Users.class);
        Root<Users> userRoot = cq.from(Users.class);
        cq.select(userRoot);
        cq.where(cb.equal(userRoot.get("userName"), userName));
        return em.createQuery(cq).getResultList();
    }
}