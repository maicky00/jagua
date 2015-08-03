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
import entidades.Detallefactura;
import entidades.Facturas;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class FacturasJpaController implements Serializable {

    public FacturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturas facturas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura iddetallefac = facturas.getIddetallefac();
            if (iddetallefac != null) {
                iddetallefac = em.getReference(iddetallefac.getClass(), iddetallefac.getIddetallefac());
                facturas.setIddetallefac(iddetallefac);
            }
            em.persist(facturas);
            if (iddetallefac != null) {
                iddetallefac.getFacturasList().add(facturas);
                iddetallefac = em.merge(iddetallefac);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturas facturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas persistentFacturas = em.find(Facturas.class, facturas.getIdfactura());
            Detallefactura iddetallefacOld = persistentFacturas.getIddetallefac();
            Detallefactura iddetallefacNew = facturas.getIddetallefac();
            if (iddetallefacNew != null) {
                iddetallefacNew = em.getReference(iddetallefacNew.getClass(), iddetallefacNew.getIddetallefac());
                facturas.setIddetallefac(iddetallefacNew);
            }
            facturas = em.merge(facturas);
            if (iddetallefacOld != null && !iddetallefacOld.equals(iddetallefacNew)) {
                iddetallefacOld.getFacturasList().remove(facturas);
                iddetallefacOld = em.merge(iddetallefacOld);
            }
            if (iddetallefacNew != null && !iddetallefacNew.equals(iddetallefacOld)) {
                iddetallefacNew.getFacturasList().add(facturas);
                iddetallefacNew = em.merge(iddetallefacNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturas.getIdfactura();
                if (findFacturas(id) == null) {
                    throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.");
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
            Facturas facturas;
            try {
                facturas = em.getReference(Facturas.class, id);
                facturas.getIdfactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.", enfe);
            }
            Detallefactura iddetallefac = facturas.getIddetallefac();
            if (iddetallefac != null) {
                iddetallefac.getFacturasList().remove(facturas);
                iddetallefac = em.merge(iddetallefac);
            }
            em.remove(facturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturas> findFacturasEntities() {
        return findFacturasEntities(true, -1, -1);
    }

    public List<Facturas> findFacturasEntities(int maxResults, int firstResult) {
        return findFacturasEntities(false, maxResults, firstResult);
    }

    private List<Facturas> findFacturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturas.class));
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

    public Facturas findFacturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturas> rt = cq.from(Facturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
