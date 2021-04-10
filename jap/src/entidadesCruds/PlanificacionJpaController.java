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
import entidades.Planificacion;
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
public class PlanificacionJpaController implements Serializable {

    public PlanificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planificacion planificacion) {
        if (planificacion.getAsistenciaCollection() == null) {
            planificacion.setAsistenciaCollection(new ArrayList<Asistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Asistencia> attachedAsistenciaCollection = new ArrayList<Asistencia>();
            for (Asistencia asistenciaCollectionAsistenciaToAttach : planificacion.getAsistenciaCollection()) {
                asistenciaCollectionAsistenciaToAttach = em.getReference(asistenciaCollectionAsistenciaToAttach.getClass(), asistenciaCollectionAsistenciaToAttach.getIdasistencia());
                attachedAsistenciaCollection.add(asistenciaCollectionAsistenciaToAttach);
            }
            planificacion.setAsistenciaCollection(attachedAsistenciaCollection);
            em.persist(planificacion);
            for (Asistencia asistenciaCollectionAsistencia : planificacion.getAsistenciaCollection()) {
                Planificacion oldIdplanificacionOfAsistenciaCollectionAsistencia = asistenciaCollectionAsistencia.getIdplanificacion();
                asistenciaCollectionAsistencia.setIdplanificacion(planificacion);
                asistenciaCollectionAsistencia = em.merge(asistenciaCollectionAsistencia);
                if (oldIdplanificacionOfAsistenciaCollectionAsistencia != null) {
                    oldIdplanificacionOfAsistenciaCollectionAsistencia.getAsistenciaCollection().remove(asistenciaCollectionAsistencia);
                    oldIdplanificacionOfAsistenciaCollectionAsistencia = em.merge(oldIdplanificacionOfAsistenciaCollectionAsistencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planificacion planificacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacion persistentPlanificacion = em.find(Planificacion.class, planificacion.getIdplanificacion());
            Collection<Asistencia> asistenciaCollectionOld = persistentPlanificacion.getAsistenciaCollection();
            Collection<Asistencia> asistenciaCollectionNew = planificacion.getAsistenciaCollection();
            List<String> illegalOrphanMessages = null;
            for (Asistencia asistenciaCollectionOldAsistencia : asistenciaCollectionOld) {
                if (!asistenciaCollectionNew.contains(asistenciaCollectionOldAsistencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asistencia " + asistenciaCollectionOldAsistencia + " since its idplanificacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Asistencia> attachedAsistenciaCollectionNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaCollectionNewAsistenciaToAttach : asistenciaCollectionNew) {
                asistenciaCollectionNewAsistenciaToAttach = em.getReference(asistenciaCollectionNewAsistenciaToAttach.getClass(), asistenciaCollectionNewAsistenciaToAttach.getIdasistencia());
                attachedAsistenciaCollectionNew.add(asistenciaCollectionNewAsistenciaToAttach);
            }
            asistenciaCollectionNew = attachedAsistenciaCollectionNew;
            planificacion.setAsistenciaCollection(asistenciaCollectionNew);
            planificacion = em.merge(planificacion);
            for (Asistencia asistenciaCollectionNewAsistencia : asistenciaCollectionNew) {
                if (!asistenciaCollectionOld.contains(asistenciaCollectionNewAsistencia)) {
                    Planificacion oldIdplanificacionOfAsistenciaCollectionNewAsistencia = asistenciaCollectionNewAsistencia.getIdplanificacion();
                    asistenciaCollectionNewAsistencia.setIdplanificacion(planificacion);
                    asistenciaCollectionNewAsistencia = em.merge(asistenciaCollectionNewAsistencia);
                    if (oldIdplanificacionOfAsistenciaCollectionNewAsistencia != null && !oldIdplanificacionOfAsistenciaCollectionNewAsistencia.equals(planificacion)) {
                        oldIdplanificacionOfAsistenciaCollectionNewAsistencia.getAsistenciaCollection().remove(asistenciaCollectionNewAsistencia);
                        oldIdplanificacionOfAsistenciaCollectionNewAsistencia = em.merge(oldIdplanificacionOfAsistenciaCollectionNewAsistencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planificacion.getIdplanificacion();
                if (findPlanificacion(id) == null) {
                    throw new NonexistentEntityException("The planificacion with id " + id + " no longer exists.");
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
            Planificacion planificacion;
            try {
                planificacion = em.getReference(Planificacion.class, id);
                planificacion.getIdplanificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planificacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Asistencia> asistenciaCollectionOrphanCheck = planificacion.getAsistenciaCollection();
            for (Asistencia asistenciaCollectionOrphanCheckAsistencia : asistenciaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacion (" + planificacion + ") cannot be destroyed since the Asistencia " + asistenciaCollectionOrphanCheckAsistencia + " in its asistenciaCollection field has a non-nullable idplanificacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(planificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planificacion> findPlanificacionEntities() {
        return findPlanificacionEntities(true, -1, -1);
    }

    public List<Planificacion> findPlanificacionEntities(int maxResults, int firstResult) {
        return findPlanificacionEntities(false, maxResults, firstResult);
    }

    private List<Planificacion> findPlanificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planificacion.class));
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

    public Planificacion findPlanificacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planificacion> rt = cq.from(Planificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
