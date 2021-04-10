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
import entidades.Detallefacturasobrante;
import entidades.Facturassobrante;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tech-Usuario
 */
public class FacturassobranteJpaController implements Serializable {

    public FacturassobranteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturassobrante facturassobrante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefacturasobrante iddetallefacsobrante = facturassobrante.getIddetallefacsobrante();
            if (iddetallefacsobrante != null) {
                iddetallefacsobrante = em.getReference(iddetallefacsobrante.getClass(), iddetallefacsobrante.getIddetallefacsobrante());
                facturassobrante.setIddetallefacsobrante(iddetallefacsobrante);
            }
            em.persist(facturassobrante);
            if (iddetallefacsobrante != null) {
                iddetallefacsobrante.getFacturassobranteCollection().add(facturassobrante);
                iddetallefacsobrante = em.merge(iddetallefacsobrante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturassobrante facturassobrante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturassobrante persistentFacturassobrante = em.find(Facturassobrante.class, facturassobrante.getIdfacturasobrante());
            Detallefacturasobrante iddetallefacsobranteOld = persistentFacturassobrante.getIddetallefacsobrante();
            Detallefacturasobrante iddetallefacsobranteNew = facturassobrante.getIddetallefacsobrante();
            if (iddetallefacsobranteNew != null) {
                iddetallefacsobranteNew = em.getReference(iddetallefacsobranteNew.getClass(), iddetallefacsobranteNew.getIddetallefacsobrante());
                facturassobrante.setIddetallefacsobrante(iddetallefacsobranteNew);
            }
            facturassobrante = em.merge(facturassobrante);
            if (iddetallefacsobranteOld != null && !iddetallefacsobranteOld.equals(iddetallefacsobranteNew)) {
                iddetallefacsobranteOld.getFacturassobranteCollection().remove(facturassobrante);
                iddetallefacsobranteOld = em.merge(iddetallefacsobranteOld);
            }
            if (iddetallefacsobranteNew != null && !iddetallefacsobranteNew.equals(iddetallefacsobranteOld)) {
                iddetallefacsobranteNew.getFacturassobranteCollection().add(facturassobrante);
                iddetallefacsobranteNew = em.merge(iddetallefacsobranteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturassobrante.getIdfacturasobrante();
                if (findFacturassobrante(id) == null) {
                    throw new NonexistentEntityException("The facturassobrante with id " + id + " no longer exists.");
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
            Facturassobrante facturassobrante;
            try {
                facturassobrante = em.getReference(Facturassobrante.class, id);
                facturassobrante.getIdfacturasobrante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturassobrante with id " + id + " no longer exists.", enfe);
            }
            Detallefacturasobrante iddetallefacsobrante = facturassobrante.getIddetallefacsobrante();
            if (iddetallefacsobrante != null) {
                iddetallefacsobrante.getFacturassobranteCollection().remove(facturassobrante);
                iddetallefacsobrante = em.merge(iddetallefacsobrante);
            }
            em.remove(facturassobrante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturassobrante> findFacturassobranteEntities() {
        return findFacturassobranteEntities(true, -1, -1);
    }

    public List<Facturassobrante> findFacturassobranteEntities(int maxResults, int firstResult) {
        return findFacturassobranteEntities(false, maxResults, firstResult);
    }

    private List<Facturassobrante> findFacturassobranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturassobrante.class));
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

    public Facturassobrante findFacturassobrante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturassobrante.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturassobranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturassobrante> rt = cq.from(Facturassobrante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
