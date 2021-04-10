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
import entidades.Tarifas;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tech-Usuario
 */
public class TarifasJpaController implements Serializable {

    public TarifasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarifas tarifas) {
        if (tarifas.getDetallefacturaCollection() == null) {
            tarifas.setDetallefacturaCollection(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Detallefactura> attachedDetallefacturaCollection = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaCollectionDetallefacturaToAttach : tarifas.getDetallefacturaCollection()) {
                detallefacturaCollectionDetallefacturaToAttach = em.getReference(detallefacturaCollectionDetallefacturaToAttach.getClass(), detallefacturaCollectionDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaCollection.add(detallefacturaCollectionDetallefacturaToAttach);
            }
            tarifas.setDetallefacturaCollection(attachedDetallefacturaCollection);
            em.persist(tarifas);
            for (Detallefactura detallefacturaCollectionDetallefactura : tarifas.getDetallefacturaCollection()) {
                Tarifas oldIdtarifasOfDetallefacturaCollectionDetallefactura = detallefacturaCollectionDetallefactura.getIdtarifas();
                detallefacturaCollectionDetallefactura.setIdtarifas(tarifas);
                detallefacturaCollectionDetallefactura = em.merge(detallefacturaCollectionDetallefactura);
                if (oldIdtarifasOfDetallefacturaCollectionDetallefactura != null) {
                    oldIdtarifasOfDetallefacturaCollectionDetallefactura.getDetallefacturaCollection().remove(detallefacturaCollectionDetallefactura);
                    oldIdtarifasOfDetallefacturaCollectionDetallefactura = em.merge(oldIdtarifasOfDetallefacturaCollectionDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarifas tarifas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarifas persistentTarifas = em.find(Tarifas.class, tarifas.getIdtarifas());
            Collection<Detallefactura> detallefacturaCollectionOld = persistentTarifas.getDetallefacturaCollection();
            Collection<Detallefactura> detallefacturaCollectionNew = tarifas.getDetallefacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (Detallefactura detallefacturaCollectionOldDetallefactura : detallefacturaCollectionOld) {
                if (!detallefacturaCollectionNew.contains(detallefacturaCollectionOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaCollectionOldDetallefactura + " since its idtarifas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Detallefactura> attachedDetallefacturaCollectionNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaCollectionNewDetallefacturaToAttach : detallefacturaCollectionNew) {
                detallefacturaCollectionNewDetallefacturaToAttach = em.getReference(detallefacturaCollectionNewDetallefacturaToAttach.getClass(), detallefacturaCollectionNewDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaCollectionNew.add(detallefacturaCollectionNewDetallefacturaToAttach);
            }
            detallefacturaCollectionNew = attachedDetallefacturaCollectionNew;
            tarifas.setDetallefacturaCollection(detallefacturaCollectionNew);
            tarifas = em.merge(tarifas);
            for (Detallefactura detallefacturaCollectionNewDetallefactura : detallefacturaCollectionNew) {
                if (!detallefacturaCollectionOld.contains(detallefacturaCollectionNewDetallefactura)) {
                    Tarifas oldIdtarifasOfDetallefacturaCollectionNewDetallefactura = detallefacturaCollectionNewDetallefactura.getIdtarifas();
                    detallefacturaCollectionNewDetallefactura.setIdtarifas(tarifas);
                    detallefacturaCollectionNewDetallefactura = em.merge(detallefacturaCollectionNewDetallefactura);
                    if (oldIdtarifasOfDetallefacturaCollectionNewDetallefactura != null && !oldIdtarifasOfDetallefacturaCollectionNewDetallefactura.equals(tarifas)) {
                        oldIdtarifasOfDetallefacturaCollectionNewDetallefactura.getDetallefacturaCollection().remove(detallefacturaCollectionNewDetallefactura);
                        oldIdtarifasOfDetallefacturaCollectionNewDetallefactura = em.merge(oldIdtarifasOfDetallefacturaCollectionNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tarifas.getIdtarifas();
                if (findTarifas(id) == null) {
                    throw new NonexistentEntityException("The tarifas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarifas tarifas;
            try {
                tarifas = em.getReference(Tarifas.class, id);
                tarifas.getIdtarifas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarifas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detallefactura> detallefacturaCollectionOrphanCheck = tarifas.getDetallefacturaCollection();
            for (Detallefactura detallefacturaCollectionOrphanCheckDetallefactura : detallefacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tarifas (" + tarifas + ") cannot be destroyed since the Detallefactura " + detallefacturaCollectionOrphanCheckDetallefactura + " in its detallefacturaCollection field has a non-nullable idtarifas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tarifas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarifas> findTarifasEntities() {
        return findTarifasEntities(true, -1, -1);
    }

    public List<Tarifas> findTarifasEntities(int maxResults, int firstResult) {
        return findTarifasEntities(false, maxResults, firstResult);
    }

    private List<Tarifas> findTarifasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarifas.class));
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

    public Tarifas findTarifas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarifas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarifasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarifas> rt = cq.from(Tarifas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
