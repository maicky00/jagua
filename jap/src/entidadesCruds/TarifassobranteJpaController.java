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
import entidades.Tarifassobrante;
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
public class TarifassobranteJpaController implements Serializable {

    public TarifassobranteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarifassobrante tarifassobrante) {
        if (tarifassobrante.getDetallefacturasobranteCollection() == null) {
            tarifassobrante.setDetallefacturasobranteCollection(new ArrayList<Detallefacturasobrante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Detallefacturasobrante> attachedDetallefacturasobranteCollection = new ArrayList<Detallefacturasobrante>();
            for (Detallefacturasobrante detallefacturasobranteCollectionDetallefacturasobranteToAttach : tarifassobrante.getDetallefacturasobranteCollection()) {
                detallefacturasobranteCollectionDetallefacturasobranteToAttach = em.getReference(detallefacturasobranteCollectionDetallefacturasobranteToAttach.getClass(), detallefacturasobranteCollectionDetallefacturasobranteToAttach.getIddetallefacsobrante());
                attachedDetallefacturasobranteCollection.add(detallefacturasobranteCollectionDetallefacturasobranteToAttach);
            }
            tarifassobrante.setDetallefacturasobranteCollection(attachedDetallefacturasobranteCollection);
            em.persist(tarifassobrante);
            for (Detallefacturasobrante detallefacturasobranteCollectionDetallefacturasobrante : tarifassobrante.getDetallefacturasobranteCollection()) {
                Tarifassobrante oldIdtarifassobranteOfDetallefacturasobranteCollectionDetallefacturasobrante = detallefacturasobranteCollectionDetallefacturasobrante.getIdtarifassobrante();
                detallefacturasobranteCollectionDetallefacturasobrante.setIdtarifassobrante(tarifassobrante);
                detallefacturasobranteCollectionDetallefacturasobrante = em.merge(detallefacturasobranteCollectionDetallefacturasobrante);
                if (oldIdtarifassobranteOfDetallefacturasobranteCollectionDetallefacturasobrante != null) {
                    oldIdtarifassobranteOfDetallefacturasobranteCollectionDetallefacturasobrante.getDetallefacturasobranteCollection().remove(detallefacturasobranteCollectionDetallefacturasobrante);
                    oldIdtarifassobranteOfDetallefacturasobranteCollectionDetallefacturasobrante = em.merge(oldIdtarifassobranteOfDetallefacturasobranteCollectionDetallefacturasobrante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarifassobrante tarifassobrante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarifassobrante persistentTarifassobrante = em.find(Tarifassobrante.class, tarifassobrante.getIdtarifassobrante());
            Collection<Detallefacturasobrante> detallefacturasobranteCollectionOld = persistentTarifassobrante.getDetallefacturasobranteCollection();
            Collection<Detallefacturasobrante> detallefacturasobranteCollectionNew = tarifassobrante.getDetallefacturasobranteCollection();
            List<String> illegalOrphanMessages = null;
            for (Detallefacturasobrante detallefacturasobranteCollectionOldDetallefacturasobrante : detallefacturasobranteCollectionOld) {
                if (!detallefacturasobranteCollectionNew.contains(detallefacturasobranteCollectionOldDetallefacturasobrante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefacturasobrante " + detallefacturasobranteCollectionOldDetallefacturasobrante + " since its idtarifassobrante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Detallefacturasobrante> attachedDetallefacturasobranteCollectionNew = new ArrayList<Detallefacturasobrante>();
            for (Detallefacturasobrante detallefacturasobranteCollectionNewDetallefacturasobranteToAttach : detallefacturasobranteCollectionNew) {
                detallefacturasobranteCollectionNewDetallefacturasobranteToAttach = em.getReference(detallefacturasobranteCollectionNewDetallefacturasobranteToAttach.getClass(), detallefacturasobranteCollectionNewDetallefacturasobranteToAttach.getIddetallefacsobrante());
                attachedDetallefacturasobranteCollectionNew.add(detallefacturasobranteCollectionNewDetallefacturasobranteToAttach);
            }
            detallefacturasobranteCollectionNew = attachedDetallefacturasobranteCollectionNew;
            tarifassobrante.setDetallefacturasobranteCollection(detallefacturasobranteCollectionNew);
            tarifassobrante = em.merge(tarifassobrante);
            for (Detallefacturasobrante detallefacturasobranteCollectionNewDetallefacturasobrante : detallefacturasobranteCollectionNew) {
                if (!detallefacturasobranteCollectionOld.contains(detallefacturasobranteCollectionNewDetallefacturasobrante)) {
                    Tarifassobrante oldIdtarifassobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante = detallefacturasobranteCollectionNewDetallefacturasobrante.getIdtarifassobrante();
                    detallefacturasobranteCollectionNewDetallefacturasobrante.setIdtarifassobrante(tarifassobrante);
                    detallefacturasobranteCollectionNewDetallefacturasobrante = em.merge(detallefacturasobranteCollectionNewDetallefacturasobrante);
                    if (oldIdtarifassobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante != null && !oldIdtarifassobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante.equals(tarifassobrante)) {
                        oldIdtarifassobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante.getDetallefacturasobranteCollection().remove(detallefacturasobranteCollectionNewDetallefacturasobrante);
                        oldIdtarifassobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante = em.merge(oldIdtarifassobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tarifassobrante.getIdtarifassobrante();
                if (findTarifassobrante(id) == null) {
                    throw new NonexistentEntityException("The tarifassobrante with id " + id + " no longer exists.");
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
            Tarifassobrante tarifassobrante;
            try {
                tarifassobrante = em.getReference(Tarifassobrante.class, id);
                tarifassobrante.getIdtarifassobrante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarifassobrante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detallefacturasobrante> detallefacturasobranteCollectionOrphanCheck = tarifassobrante.getDetallefacturasobranteCollection();
            for (Detallefacturasobrante detallefacturasobranteCollectionOrphanCheckDetallefacturasobrante : detallefacturasobranteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tarifassobrante (" + tarifassobrante + ") cannot be destroyed since the Detallefacturasobrante " + detallefacturasobranteCollectionOrphanCheckDetallefacturasobrante + " in its detallefacturasobranteCollection field has a non-nullable idtarifassobrante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tarifassobrante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarifassobrante> findTarifassobranteEntities() {
        return findTarifassobranteEntities(true, -1, -1);
    }

    public List<Tarifassobrante> findTarifassobranteEntities(int maxResults, int firstResult) {
        return findTarifassobranteEntities(false, maxResults, firstResult);
    }

    private List<Tarifassobrante> findTarifassobranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarifassobrante.class));
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

    public Tarifassobrante findTarifassobrante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarifassobrante.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarifassobranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarifassobrante> rt = cq.from(Tarifassobrante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
