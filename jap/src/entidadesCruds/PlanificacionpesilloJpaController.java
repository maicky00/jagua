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
import entidades.Planificacionpesillo;
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
public class PlanificacionpesilloJpaController implements Serializable {

    public PlanificacionpesilloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planificacionpesillo planificacionpesillo) {
        if (planificacionpesillo.getAsistenciapesilloCollection() == null) {
            planificacionpesillo.setAsistenciapesilloCollection(new ArrayList<Asistenciapesillo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Asistenciapesillo> attachedAsistenciapesilloCollection = new ArrayList<Asistenciapesillo>();
            for (Asistenciapesillo asistenciapesilloCollectionAsistenciapesilloToAttach : planificacionpesillo.getAsistenciapesilloCollection()) {
                asistenciapesilloCollectionAsistenciapesilloToAttach = em.getReference(asistenciapesilloCollectionAsistenciapesilloToAttach.getClass(), asistenciapesilloCollectionAsistenciapesilloToAttach.getIdasistenciapesillo());
                attachedAsistenciapesilloCollection.add(asistenciapesilloCollectionAsistenciapesilloToAttach);
            }
            planificacionpesillo.setAsistenciapesilloCollection(attachedAsistenciapesilloCollection);
            em.persist(planificacionpesillo);
            for (Asistenciapesillo asistenciapesilloCollectionAsistenciapesillo : planificacionpesillo.getAsistenciapesilloCollection()) {
                Planificacionpesillo oldIdplanificacionpesilloOfAsistenciapesilloCollectionAsistenciapesillo = asistenciapesilloCollectionAsistenciapesillo.getIdplanificacionpesillo();
                asistenciapesilloCollectionAsistenciapesillo.setIdplanificacionpesillo(planificacionpesillo);
                asistenciapesilloCollectionAsistenciapesillo = em.merge(asistenciapesilloCollectionAsistenciapesillo);
                if (oldIdplanificacionpesilloOfAsistenciapesilloCollectionAsistenciapesillo != null) {
                    oldIdplanificacionpesilloOfAsistenciapesilloCollectionAsistenciapesillo.getAsistenciapesilloCollection().remove(asistenciapesilloCollectionAsistenciapesillo);
                    oldIdplanificacionpesilloOfAsistenciapesilloCollectionAsistenciapesillo = em.merge(oldIdplanificacionpesilloOfAsistenciapesilloCollectionAsistenciapesillo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planificacionpesillo planificacionpesillo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacionpesillo persistentPlanificacionpesillo = em.find(Planificacionpesillo.class, planificacionpesillo.getIdplanificacionpesillo());
            Collection<Asistenciapesillo> asistenciapesilloCollectionOld = persistentPlanificacionpesillo.getAsistenciapesilloCollection();
            Collection<Asistenciapesillo> asistenciapesilloCollectionNew = planificacionpesillo.getAsistenciapesilloCollection();
            List<String> illegalOrphanMessages = null;
            for (Asistenciapesillo asistenciapesilloCollectionOldAsistenciapesillo : asistenciapesilloCollectionOld) {
                if (!asistenciapesilloCollectionNew.contains(asistenciapesilloCollectionOldAsistenciapesillo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asistenciapesillo " + asistenciapesilloCollectionOldAsistenciapesillo + " since its idplanificacionpesillo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Asistenciapesillo> attachedAsistenciapesilloCollectionNew = new ArrayList<Asistenciapesillo>();
            for (Asistenciapesillo asistenciapesilloCollectionNewAsistenciapesilloToAttach : asistenciapesilloCollectionNew) {
                asistenciapesilloCollectionNewAsistenciapesilloToAttach = em.getReference(asistenciapesilloCollectionNewAsistenciapesilloToAttach.getClass(), asistenciapesilloCollectionNewAsistenciapesilloToAttach.getIdasistenciapesillo());
                attachedAsistenciapesilloCollectionNew.add(asistenciapesilloCollectionNewAsistenciapesilloToAttach);
            }
            asistenciapesilloCollectionNew = attachedAsistenciapesilloCollectionNew;
            planificacionpesillo.setAsistenciapesilloCollection(asistenciapesilloCollectionNew);
            planificacionpesillo = em.merge(planificacionpesillo);
            for (Asistenciapesillo asistenciapesilloCollectionNewAsistenciapesillo : asistenciapesilloCollectionNew) {
                if (!asistenciapesilloCollectionOld.contains(asistenciapesilloCollectionNewAsistenciapesillo)) {
                    Planificacionpesillo oldIdplanificacionpesilloOfAsistenciapesilloCollectionNewAsistenciapesillo = asistenciapesilloCollectionNewAsistenciapesillo.getIdplanificacionpesillo();
                    asistenciapesilloCollectionNewAsistenciapesillo.setIdplanificacionpesillo(planificacionpesillo);
                    asistenciapesilloCollectionNewAsistenciapesillo = em.merge(asistenciapesilloCollectionNewAsistenciapesillo);
                    if (oldIdplanificacionpesilloOfAsistenciapesilloCollectionNewAsistenciapesillo != null && !oldIdplanificacionpesilloOfAsistenciapesilloCollectionNewAsistenciapesillo.equals(planificacionpesillo)) {
                        oldIdplanificacionpesilloOfAsistenciapesilloCollectionNewAsistenciapesillo.getAsistenciapesilloCollection().remove(asistenciapesilloCollectionNewAsistenciapesillo);
                        oldIdplanificacionpesilloOfAsistenciapesilloCollectionNewAsistenciapesillo = em.merge(oldIdplanificacionpesilloOfAsistenciapesilloCollectionNewAsistenciapesillo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planificacionpesillo.getIdplanificacionpesillo();
                if (findPlanificacionpesillo(id) == null) {
                    throw new NonexistentEntityException("The planificacionpesillo with id " + id + " no longer exists.");
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
            Planificacionpesillo planificacionpesillo;
            try {
                planificacionpesillo = em.getReference(Planificacionpesillo.class, id);
                planificacionpesillo.getIdplanificacionpesillo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planificacionpesillo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Asistenciapesillo> asistenciapesilloCollectionOrphanCheck = planificacionpesillo.getAsistenciapesilloCollection();
            for (Asistenciapesillo asistenciapesilloCollectionOrphanCheckAsistenciapesillo : asistenciapesilloCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacionpesillo (" + planificacionpesillo + ") cannot be destroyed since the Asistenciapesillo " + asistenciapesilloCollectionOrphanCheckAsistenciapesillo + " in its asistenciapesilloCollection field has a non-nullable idplanificacionpesillo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(planificacionpesillo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planificacionpesillo> findPlanificacionpesilloEntities() {
        return findPlanificacionpesilloEntities(true, -1, -1);
    }

    public List<Planificacionpesillo> findPlanificacionpesilloEntities(int maxResults, int firstResult) {
        return findPlanificacionpesilloEntities(false, maxResults, firstResult);
    }

    private List<Planificacionpesillo> findPlanificacionpesilloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planificacionpesillo.class));
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

    public Planificacionpesillo findPlanificacionpesillo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planificacionpesillo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanificacionpesilloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planificacionpesillo> rt = cq.from(Planificacionpesillo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
