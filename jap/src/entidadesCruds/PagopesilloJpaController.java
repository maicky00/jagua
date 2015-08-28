/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Asistenciapesillo;
import entidades.Pagopesillo;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Marco
 */
public class PagopesilloJpaController implements Serializable {

    public PagopesilloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagopesillo pagopesillo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistenciapesillo idasistenciapesillo = pagopesillo.getIdasistenciapesillo();
            if (idasistenciapesillo != null) {
                idasistenciapesillo = em.getReference(idasistenciapesillo.getClass(), idasistenciapesillo.getIdasistenciapesillo());
                pagopesillo.setIdasistenciapesillo(idasistenciapesillo);
            }
            em.persist(pagopesillo);
            if (idasistenciapesillo != null) {
                idasistenciapesillo.getPagopesilloList().add(pagopesillo);
                idasistenciapesillo = em.merge(idasistenciapesillo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagopesillo pagopesillo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagopesillo persistentPagopesillo = em.find(Pagopesillo.class, pagopesillo.getIdpagopesillo());
            Asistenciapesillo idasistenciapesilloOld = persistentPagopesillo.getIdasistenciapesillo();
            Asistenciapesillo idasistenciapesilloNew = pagopesillo.getIdasistenciapesillo();
            if (idasistenciapesilloNew != null) {
                idasistenciapesilloNew = em.getReference(idasistenciapesilloNew.getClass(), idasistenciapesilloNew.getIdasistenciapesillo());
                pagopesillo.setIdasistenciapesillo(idasistenciapesilloNew);
            }
            pagopesillo = em.merge(pagopesillo);
            if (idasistenciapesilloOld != null && !idasistenciapesilloOld.equals(idasistenciapesilloNew)) {
                idasistenciapesilloOld.getPagopesilloList().remove(pagopesillo);
                idasistenciapesilloOld = em.merge(idasistenciapesilloOld);
            }
            if (idasistenciapesilloNew != null && !idasistenciapesilloNew.equals(idasistenciapesilloOld)) {
                idasistenciapesilloNew.getPagopesilloList().add(pagopesillo);
                idasistenciapesilloNew = em.merge(idasistenciapesilloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagopesillo.getIdpagopesillo();
                if (findPagopesillo(id) == null) {
                    throw new NonexistentEntityException("The pagopesillo with id " + id + " no longer exists.");
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
            Pagopesillo pagopesillo;
            try {
                pagopesillo = em.getReference(Pagopesillo.class, id);
                pagopesillo.getIdpagopesillo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagopesillo with id " + id + " no longer exists.", enfe);
            }
            Asistenciapesillo idasistenciapesillo = pagopesillo.getIdasistenciapesillo();
            if (idasistenciapesillo != null) {
                idasistenciapesillo.getPagopesilloList().remove(pagopesillo);
                idasistenciapesillo = em.merge(idasistenciapesillo);
            }
            em.remove(pagopesillo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagopesillo> findPagopesilloEntities() {
        return findPagopesilloEntities(true, -1, -1);
    }

    public List<Pagopesillo> findPagopesilloEntities(int maxResults, int firstResult) {
        return findPagopesilloEntities(false, maxResults, firstResult);
    }

    private List<Pagopesillo> findPagopesilloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagopesillo.class));
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

    public Pagopesillo findPagopesillo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagopesillo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagopesilloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagopesillo> rt = cq.from(Pagopesillo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
