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
import entidades.Medidor;
import entidades.Pagosnuevomed;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tech-Usuario
 */
public class PagosnuevomedJpaController implements Serializable {

    public PagosnuevomedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagosnuevomed pagosnuevomed) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidor idmedidor = pagosnuevomed.getIdmedidor();
            if (idmedidor != null) {
                idmedidor = em.getReference(idmedidor.getClass(), idmedidor.getIdmedidor());
                pagosnuevomed.setIdmedidor(idmedidor);
            }
            em.persist(pagosnuevomed);
            if (idmedidor != null) {
                idmedidor.getPagosnuevomedCollection().add(pagosnuevomed);
                idmedidor = em.merge(idmedidor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagosnuevomed pagosnuevomed) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagosnuevomed persistentPagosnuevomed = em.find(Pagosnuevomed.class, pagosnuevomed.getIdnuevomed());
            Medidor idmedidorOld = persistentPagosnuevomed.getIdmedidor();
            Medidor idmedidorNew = pagosnuevomed.getIdmedidor();
            if (idmedidorNew != null) {
                idmedidorNew = em.getReference(idmedidorNew.getClass(), idmedidorNew.getIdmedidor());
                pagosnuevomed.setIdmedidor(idmedidorNew);
            }
            pagosnuevomed = em.merge(pagosnuevomed);
            if (idmedidorOld != null && !idmedidorOld.equals(idmedidorNew)) {
                idmedidorOld.getPagosnuevomedCollection().remove(pagosnuevomed);
                idmedidorOld = em.merge(idmedidorOld);
            }
            if (idmedidorNew != null && !idmedidorNew.equals(idmedidorOld)) {
                idmedidorNew.getPagosnuevomedCollection().add(pagosnuevomed);
                idmedidorNew = em.merge(idmedidorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagosnuevomed.getIdnuevomed();
                if (findPagosnuevomed(id) == null) {
                    throw new NonexistentEntityException("The pagosnuevomed with id " + id + " no longer exists.");
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
            Pagosnuevomed pagosnuevomed;
            try {
                pagosnuevomed = em.getReference(Pagosnuevomed.class, id);
                pagosnuevomed.getIdnuevomed();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagosnuevomed with id " + id + " no longer exists.", enfe);
            }
            Medidor idmedidor = pagosnuevomed.getIdmedidor();
            if (idmedidor != null) {
                idmedidor.getPagosnuevomedCollection().remove(pagosnuevomed);
                idmedidor = em.merge(idmedidor);
            }
            em.remove(pagosnuevomed);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagosnuevomed> findPagosnuevomedEntities() {
        return findPagosnuevomedEntities(true, -1, -1);
    }

    public List<Pagosnuevomed> findPagosnuevomedEntities(int maxResults, int firstResult) {
        return findPagosnuevomedEntities(false, maxResults, firstResult);
    }

    private List<Pagosnuevomed> findPagosnuevomedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagosnuevomed.class));
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

    public Pagosnuevomed findPagosnuevomed(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagosnuevomed.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagosnuevomedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagosnuevomed> rt = cq.from(Pagosnuevomed.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
