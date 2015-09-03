/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Historialfactura;
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
public class HistorialfacturaJpaController implements Serializable {

    public HistorialfacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historialfactura historialfactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historialfactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialfactura historialfactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historialfactura = em.merge(historialfactura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historialfactura.getId();
                if (findHistorialfactura(id) == null) {
                    throw new NonexistentEntityException("The historialfactura with id " + id + " no longer exists.");
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
            Historialfactura historialfactura;
            try {
                historialfactura = em.getReference(Historialfactura.class, id);
                historialfactura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialfactura with id " + id + " no longer exists.", enfe);
            }
            em.remove(historialfactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialfactura> findHistorialfacturaEntities() {
        return findHistorialfacturaEntities(true, -1, -1);
    }

    public List<Historialfactura> findHistorialfacturaEntities(int maxResults, int firstResult) {
        return findHistorialfacturaEntities(false, maxResults, firstResult);
    }

    private List<Historialfactura> findHistorialfacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialfactura.class));
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

    public Historialfactura findHistorialfactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialfactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialfacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialfactura> rt = cq.from(Historialfactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
