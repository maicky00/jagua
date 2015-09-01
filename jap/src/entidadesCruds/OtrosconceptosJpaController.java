/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Otrosconceptos;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author JC-PC
 */
public class OtrosconceptosJpaController implements Serializable {

    public OtrosconceptosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Otrosconceptos otrosconceptos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(otrosconceptos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Otrosconceptos otrosconceptos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            otrosconceptos = em.merge(otrosconceptos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = otrosconceptos.getId();
                if (findOtrosconceptos(id) == null) {
                    throw new NonexistentEntityException("The otrosconceptos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Otrosconceptos otrosconceptos;
            try {
                otrosconceptos = em.getReference(Otrosconceptos.class, id);
                otrosconceptos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The otrosconceptos with id " + id + " no longer exists.", enfe);
            }
            em.remove(otrosconceptos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Otrosconceptos> findOtrosconceptosEntities() {
        return findOtrosconceptosEntities(true, -1, -1);
    }

    public List<Otrosconceptos> findOtrosconceptosEntities(int maxResults, int firstResult) {
        return findOtrosconceptosEntities(false, maxResults, firstResult);
    }

    private List<Otrosconceptos> findOtrosconceptosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Otrosconceptos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Otrosconceptos findOtrosconceptos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Otrosconceptos.class, id);
        } finally {
            em.close();
        }
    }

    public int getOtrosconceptosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Otrosconceptos> rt = cq.from(Otrosconceptos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
