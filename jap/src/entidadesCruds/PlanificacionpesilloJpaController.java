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
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Marco
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
        if (planificacionpesillo.getAsistenciapesilloList() == null) {
            planificacionpesillo.setAsistenciapesilloList(new ArrayList<Asistenciapesillo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asistenciapesillo> attachedAsistenciapesilloList = new ArrayList<Asistenciapesillo>();
            for (Asistenciapesillo asistenciapesilloListAsistenciapesilloToAttach : planificacionpesillo.getAsistenciapesilloList()) {
                asistenciapesilloListAsistenciapesilloToAttach = em.getReference(asistenciapesilloListAsistenciapesilloToAttach.getClass(), asistenciapesilloListAsistenciapesilloToAttach.getIdasistenciapesillo());
                attachedAsistenciapesilloList.add(asistenciapesilloListAsistenciapesilloToAttach);
            }
            planificacionpesillo.setAsistenciapesilloList(attachedAsistenciapesilloList);
            em.persist(planificacionpesillo);
            for (Asistenciapesillo asistenciapesilloListAsistenciapesillo : planificacionpesillo.getAsistenciapesilloList()) {
                Planificacionpesillo oldIdplanificacionpesilloOfAsistenciapesilloListAsistenciapesillo = asistenciapesilloListAsistenciapesillo.getIdplanificacionpesillo();
                asistenciapesilloListAsistenciapesillo.setIdplanificacionpesillo(planificacionpesillo);
                asistenciapesilloListAsistenciapesillo = em.merge(asistenciapesilloListAsistenciapesillo);
                if (oldIdplanificacionpesilloOfAsistenciapesilloListAsistenciapesillo != null) {
                    oldIdplanificacionpesilloOfAsistenciapesilloListAsistenciapesillo.getAsistenciapesilloList().remove(asistenciapesilloListAsistenciapesillo);
                    oldIdplanificacionpesilloOfAsistenciapesilloListAsistenciapesillo = em.merge(oldIdplanificacionpesilloOfAsistenciapesilloListAsistenciapesillo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planificacionpesillo planificacionpesillo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacionpesillo persistentPlanificacionpesillo = em.find(Planificacionpesillo.class, planificacionpesillo.getIdplanificacionpesillo());
            List<Asistenciapesillo> asistenciapesilloListOld = persistentPlanificacionpesillo.getAsistenciapesilloList();
            List<Asistenciapesillo> asistenciapesilloListNew = planificacionpesillo.getAsistenciapesilloList();
            List<Asistenciapesillo> attachedAsistenciapesilloListNew = new ArrayList<Asistenciapesillo>();
            for (Asistenciapesillo asistenciapesilloListNewAsistenciapesilloToAttach : asistenciapesilloListNew) {
                asistenciapesilloListNewAsistenciapesilloToAttach = em.getReference(asistenciapesilloListNewAsistenciapesilloToAttach.getClass(), asistenciapesilloListNewAsistenciapesilloToAttach.getIdasistenciapesillo());
                attachedAsistenciapesilloListNew.add(asistenciapesilloListNewAsistenciapesilloToAttach);
            }
            asistenciapesilloListNew = attachedAsistenciapesilloListNew;
            planificacionpesillo.setAsistenciapesilloList(asistenciapesilloListNew);
            planificacionpesillo = em.merge(planificacionpesillo);
            for (Asistenciapesillo asistenciapesilloListOldAsistenciapesillo : asistenciapesilloListOld) {
                if (!asistenciapesilloListNew.contains(asistenciapesilloListOldAsistenciapesillo)) {
                    asistenciapesilloListOldAsistenciapesillo.setIdplanificacionpesillo(null);
                    asistenciapesilloListOldAsistenciapesillo = em.merge(asistenciapesilloListOldAsistenciapesillo);
                }
            }
            for (Asistenciapesillo asistenciapesilloListNewAsistenciapesillo : asistenciapesilloListNew) {
                if (!asistenciapesilloListOld.contains(asistenciapesilloListNewAsistenciapesillo)) {
                    Planificacionpesillo oldIdplanificacionpesilloOfAsistenciapesilloListNewAsistenciapesillo = asistenciapesilloListNewAsistenciapesillo.getIdplanificacionpesillo();
                    asistenciapesilloListNewAsistenciapesillo.setIdplanificacionpesillo(planificacionpesillo);
                    asistenciapesilloListNewAsistenciapesillo = em.merge(asistenciapesilloListNewAsistenciapesillo);
                    if (oldIdplanificacionpesilloOfAsistenciapesilloListNewAsistenciapesillo != null && !oldIdplanificacionpesilloOfAsistenciapesilloListNewAsistenciapesillo.equals(planificacionpesillo)) {
                        oldIdplanificacionpesilloOfAsistenciapesilloListNewAsistenciapesillo.getAsistenciapesilloList().remove(asistenciapesilloListNewAsistenciapesillo);
                        oldIdplanificacionpesilloOfAsistenciapesilloListNewAsistenciapesillo = em.merge(oldIdplanificacionpesilloOfAsistenciapesilloListNewAsistenciapesillo);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            List<Asistenciapesillo> asistenciapesilloList = planificacionpesillo.getAsistenciapesilloList();
            for (Asistenciapesillo asistenciapesilloListAsistenciapesillo : asistenciapesilloList) {
                asistenciapesilloListAsistenciapesillo.setIdplanificacionpesillo(null);
                asistenciapesilloListAsistenciapesillo = em.merge(asistenciapesilloListAsistenciapesillo);
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
