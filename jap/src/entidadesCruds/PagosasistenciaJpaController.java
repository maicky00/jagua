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
import entidades.Asistencia;
import entidades.Pagosasistencia;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class PagosasistenciaJpaController implements Serializable {

    public PagosasistenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagosasistencia pagosasistencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistencia idasistencia = pagosasistencia.getIdasistencia();
            if (idasistencia != null) {
                idasistencia = em.getReference(idasistencia.getClass(), idasistencia.getIdasistencia());
                pagosasistencia.setIdasistencia(idasistencia);
            }
            em.persist(pagosasistencia);
            if (idasistencia != null) {
                idasistencia.getPagosasistenciaList().add(pagosasistencia);
                idasistencia = em.merge(idasistencia);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagosasistencia pagosasistencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagosasistencia persistentPagosasistencia = em.find(Pagosasistencia.class, pagosasistencia.getIdpagoasistencia());
            Asistencia idasistenciaOld = persistentPagosasistencia.getIdasistencia();
            Asistencia idasistenciaNew = pagosasistencia.getIdasistencia();
            if (idasistenciaNew != null) {
                idasistenciaNew = em.getReference(idasistenciaNew.getClass(), idasistenciaNew.getIdasistencia());
                pagosasistencia.setIdasistencia(idasistenciaNew);
            }
            pagosasistencia = em.merge(pagosasistencia);
            if (idasistenciaOld != null && !idasistenciaOld.equals(idasistenciaNew)) {
                idasistenciaOld.getPagosasistenciaList().remove(pagosasistencia);
                idasistenciaOld = em.merge(idasistenciaOld);
            }
            if (idasistenciaNew != null && !idasistenciaNew.equals(idasistenciaOld)) {
                idasistenciaNew.getPagosasistenciaList().add(pagosasistencia);
                idasistenciaNew = em.merge(idasistenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagosasistencia.getIdpagoasistencia();
                if (findPagosasistencia(id) == null) {
                    throw new NonexistentEntityException("The pagosasistencia with id " + id + " no longer exists.");
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
            Pagosasistencia pagosasistencia;
            try {
                pagosasistencia = em.getReference(Pagosasistencia.class, id);
                pagosasistencia.getIdpagoasistencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagosasistencia with id " + id + " no longer exists.", enfe);
            }
            Asistencia idasistencia = pagosasistencia.getIdasistencia();
            if (idasistencia != null) {
                idasistencia.getPagosasistenciaList().remove(pagosasistencia);
                idasistencia = em.merge(idasistencia);
            }
            em.remove(pagosasistencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagosasistencia> findPagosasistenciaEntities() {
        return findPagosasistenciaEntities(true, -1, -1);
    }

    public List<Pagosasistencia> findPagosasistenciaEntities(int maxResults, int firstResult) {
        return findPagosasistenciaEntities(false, maxResults, firstResult);
    }

    private List<Pagosasistencia> findPagosasistenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagosasistencia.class));
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

    public Pagosasistencia findPagosasistencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagosasistencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagosasistenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagosasistencia> rt = cq.from(Pagosasistencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
