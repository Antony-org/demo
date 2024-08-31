package example.db;

import example.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

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
}