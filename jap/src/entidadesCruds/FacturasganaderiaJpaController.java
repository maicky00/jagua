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
import entidades.Detallefacturaganaderia;
import entidades.Facturasganaderia;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tech-Usuario
 */
public class FacturasganaderiaJpaController implements Serializable {

    public FacturasganaderiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturasganaderia facturasganaderia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefacturaganaderia iddetallefacganaderia = facturasganaderia.getIddetallefacganaderia();
            if (iddetallefacganaderia != null) {
                iddetallefacganaderia = em.getReference(iddetallefacganaderia.getClass(), iddetallefacganaderia.getIddetallefacganaderia());
                facturasganaderia.setIddetallefacganaderia(iddetallefacganaderia);
            }
            em.persist(facturasganaderia);
            if (iddetallefacganaderia != null) {
                iddetallefacganaderia.getFacturasganaderiaCollection().add(facturasganaderia);
                iddetallefacganaderia = em.merge(iddetallefacganaderia);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturasganaderia facturasganaderia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturasganaderia persistentFacturasganaderia = em.find(Facturasganaderia.class, facturasganaderia.getIdfacturasganaderia());
            Detallefacturaganaderia iddetallefacganaderiaOld = persistentFacturasganaderia.getIddetallefacganaderia();
            Detallefacturaganaderia iddetallefacganaderiaNew = facturasganaderia.getIddetallefacganaderia();
            if (iddetallefacganaderiaNew != null) {
                iddetallefacganaderiaNew = em.getReference(iddetallefacganaderiaNew.getClass(), iddetallefacganaderiaNew.getIddetallefacganaderia());
                facturasganaderia.setIddetallefacganaderia(iddetallefacganaderiaNew);
            }
            facturasganaderia = em.merge(facturasganaderia);
            if (iddetallefacganaderiaOld != null && !iddetallefacganaderiaOld.equals(iddetallefacganaderiaNew)) {
                iddetallefacganaderiaOld.getFacturasganaderiaCollection().remove(facturasganaderia);
                iddetallefacganaderiaOld = em.merge(iddetallefacganaderiaOld);
            }
            if (iddetallefacganaderiaNew != null && !iddetallefacganaderiaNew.equals(iddetallefacganaderiaOld)) {
                iddetallefacganaderiaNew.getFacturasganaderiaCollection().add(facturasganaderia);
                iddetallefacganaderiaNew = em.merge(iddetallefacganaderiaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturasganaderia.getIdfacturasganaderia();
                if (findFacturasganaderia(id) == null) {
                    throw new NonexistentEntityException("The facturasganaderia with id " + id + " no longer exists.");
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
            Facturasganaderia facturasganaderia;
            try {
                facturasganaderia = em.getReference(Facturasganaderia.class, id);
                facturasganaderia.getIdfacturasganaderia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturasganaderia with id " + id + " no longer exists.", enfe);
            }
            Detallefacturaganaderia iddetallefacganaderia = facturasganaderia.getIddetallefacganaderia();
            if (iddetallefacganaderia != null) {
                iddetallefacganaderia.getFacturasganaderiaCollection().remove(facturasganaderia);
                iddetallefacganaderia = em.merge(iddetallefacganaderia);
            }
            em.remove(facturasganaderia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturasganaderia> findFacturasganaderiaEntities() {
        return findFacturasganaderiaEntities(true, -1, -1);
    }

    public List<Facturasganaderia> findFacturasganaderiaEntities(int maxResults, int firstResult) {
        return findFacturasganaderiaEntities(false, maxResults, firstResult);
    }

    private List<Facturasganaderia> findFacturasganaderiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturasganaderia.class));
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

    public Facturasganaderia findFacturasganaderia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturasganaderia.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturasganaderiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturasganaderia> rt = cq.from(Facturasganaderia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
