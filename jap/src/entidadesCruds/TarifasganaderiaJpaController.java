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
import entidades.Tarifasganaderia;
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
public class TarifasganaderiaJpaController implements Serializable {

    public TarifasganaderiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarifasganaderia tarifasganaderia) {
        if (tarifasganaderia.getDetallefacturaganaderiaCollection() == null) {
            tarifasganaderia.setDetallefacturaganaderiaCollection(new ArrayList<Detallefacturaganaderia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Detallefacturaganaderia> attachedDetallefacturaganaderiaCollection = new ArrayList<Detallefacturaganaderia>();
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach : tarifasganaderia.getDetallefacturaganaderiaCollection()) {
                detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach = em.getReference(detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach.getClass(), detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach.getIddetallefacganaderia());
                attachedDetallefacturaganaderiaCollection.add(detallefacturaganaderiaCollectionDetallefacturaganaderiaToAttach);
            }
            tarifasganaderia.setDetallefacturaganaderiaCollection(attachedDetallefacturaganaderiaCollection);
            em.persist(tarifasganaderia);
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionDetallefacturaganaderia : tarifasganaderia.getDetallefacturaganaderiaCollection()) {
                Tarifasganaderia oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia = detallefacturaganaderiaCollectionDetallefacturaganaderia.getIdtarifasganaderia();
                detallefacturaganaderiaCollectionDetallefacturaganaderia.setIdtarifasganaderia(tarifasganaderia);
                detallefacturaganaderiaCollectionDetallefacturaganaderia = em.merge(detallefacturaganaderiaCollectionDetallefacturaganaderia);
                if (oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia != null) {
                    oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia.getDetallefacturaganaderiaCollection().remove(detallefacturaganaderiaCollectionDetallefacturaganaderia);
                    oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia = em.merge(oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionDetallefacturaganaderia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarifasganaderia tarifasganaderia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarifasganaderia persistentTarifasganaderia = em.find(Tarifasganaderia.class, tarifasganaderia.getIdtarifasganaderia());
            Collection<Detallefacturaganaderia> detallefacturaganaderiaCollectionOld = persistentTarifasganaderia.getDetallefacturaganaderiaCollection();
            Collection<Detallefacturaganaderia> detallefacturaganaderiaCollectionNew = tarifasganaderia.getDetallefacturaganaderiaCollection();
            List<String> illegalOrphanMessages = null;
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionOldDetallefacturaganaderia : detallefacturaganaderiaCollectionOld) {
                if (!detallefacturaganaderiaCollectionNew.contains(detallefacturaganaderiaCollectionOldDetallefacturaganaderia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefacturaganaderia " + detallefacturaganaderiaCollectionOldDetallefacturaganaderia + " since its idtarifasganaderia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Detallefacturaganaderia> attachedDetallefacturaganaderiaCollectionNew = new ArrayList<Detallefacturaganaderia>();
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach : detallefacturaganaderiaCollectionNew) {
                detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach = em.getReference(detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach.getClass(), detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach.getIddetallefacganaderia());
                attachedDetallefacturaganaderiaCollectionNew.add(detallefacturaganaderiaCollectionNewDetallefacturaganaderiaToAttach);
            }
            detallefacturaganaderiaCollectionNew = attachedDetallefacturaganaderiaCollectionNew;
            tarifasganaderia.setDetallefacturaganaderiaCollection(detallefacturaganaderiaCollectionNew);
            tarifasganaderia = em.merge(tarifasganaderia);
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionNewDetallefacturaganaderia : detallefacturaganaderiaCollectionNew) {
                if (!detallefacturaganaderiaCollectionOld.contains(detallefacturaganaderiaCollectionNewDetallefacturaganaderia)) {
                    Tarifasganaderia oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia = detallefacturaganaderiaCollectionNewDetallefacturaganaderia.getIdtarifasganaderia();
                    detallefacturaganaderiaCollectionNewDetallefacturaganaderia.setIdtarifasganaderia(tarifasganaderia);
                    detallefacturaganaderiaCollectionNewDetallefacturaganaderia = em.merge(detallefacturaganaderiaCollectionNewDetallefacturaganaderia);
                    if (oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia != null && !oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia.equals(tarifasganaderia)) {
                        oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia.getDetallefacturaganaderiaCollection().remove(detallefacturaganaderiaCollectionNewDetallefacturaganaderia);
                        oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia = em.merge(oldIdtarifasganaderiaOfDetallefacturaganaderiaCollectionNewDetallefacturaganaderia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tarifasganaderia.getIdtarifasganaderia();
                if (findTarifasganaderia(id) == null) {
                    throw new NonexistentEntityException("The tarifasganaderia with id " + id + " no longer exists.");
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
            Tarifasganaderia tarifasganaderia;
            try {
                tarifasganaderia = em.getReference(Tarifasganaderia.class, id);
                tarifasganaderia.getIdtarifasganaderia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarifasganaderia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detallefacturaganaderia> detallefacturaganaderiaCollectionOrphanCheck = tarifasganaderia.getDetallefacturaganaderiaCollection();
            for (Detallefacturaganaderia detallefacturaganaderiaCollectionOrphanCheckDetallefacturaganaderia : detallefacturaganaderiaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tarifasganaderia (" + tarifasganaderia + ") cannot be destroyed since the Detallefacturaganaderia " + detallefacturaganaderiaCollectionOrphanCheckDetallefacturaganaderia + " in its detallefacturaganaderiaCollection field has a non-nullable idtarifasganaderia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tarifasganaderia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarifasganaderia> findTarifasganaderiaEntities() {
        return findTarifasganaderiaEntities(true, -1, -1);
    }

    public List<Tarifasganaderia> findTarifasganaderiaEntities(int maxResults, int firstResult) {
        return findTarifasganaderiaEntities(false, maxResults, firstResult);
    }

    private List<Tarifasganaderia> findTarifasganaderiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarifasganaderia.class));
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

    public Tarifasganaderia findTarifasganaderia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarifasganaderia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarifasganaderiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarifasganaderia> rt = cq.from(Tarifasganaderia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
