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
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
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
        if (planificacion.getAsistenciaList() == null) {
            planificacion.setAsistenciaList(new ArrayList<Asistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListAsistenciaToAttach : planificacion.getAsistenciaList()) {
                asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdasistencia());
                attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
            }
            planificacion.setAsistenciaList(attachedAsistenciaList);
            em.persist(planificacion);
            for (Asistencia asistenciaListAsistencia : planificacion.getAsistenciaList()) {
                Planificacion oldIdplanificacionOfAsistenciaListAsistencia = asistenciaListAsistencia.getIdplanificacion();
                asistenciaListAsistencia.setIdplanificacion(planificacion);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
                if (oldIdplanificacionOfAsistenciaListAsistencia != null) {
                    oldIdplanificacionOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
                    oldIdplanificacionOfAsistenciaListAsistencia = em.merge(oldIdplanificacionOfAsistenciaListAsistencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planificacion planificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacion persistentPlanificacion = em.find(Planificacion.class, planificacion.getIdplanificacion());
            List<Asistencia> asistenciaListOld = persistentPlanificacion.getAsistenciaList();
            List<Asistencia> asistenciaListNew = planificacion.getAsistenciaList();
            List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
                asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdasistencia());
                attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
            }
            asistenciaListNew = attachedAsistenciaListNew;
            planificacion.setAsistenciaList(asistenciaListNew);
            planificacion = em.merge(planificacion);
            for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
                if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
                    asistenciaListOldAsistencia.setIdplanificacion(null);
                    asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
                }
            }
            for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
                if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
                    Planificacion oldIdplanificacionOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getIdplanificacion();
                    asistenciaListNewAsistencia.setIdplanificacion(planificacion);
                    asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
                    if (oldIdplanificacionOfAsistenciaListNewAsistencia != null && !oldIdplanificacionOfAsistenciaListNewAsistencia.equals(planificacion)) {
                        oldIdplanificacionOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
                        oldIdplanificacionOfAsistenciaListNewAsistencia = em.merge(oldIdplanificacionOfAsistenciaListNewAsistencia);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            List<Asistencia> asistenciaList = planificacion.getAsistenciaList();
            for (Asistencia asistenciaListAsistencia : asistenciaList) {
                asistenciaListAsistencia.setIdplanificacion(null);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
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
