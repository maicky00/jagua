/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Asistenciapesillo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Planificacionpesillo;
import entidades.Pagopesillo;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Marco
 */
public class AsistenciapesilloJpaController implements Serializable {

    public AsistenciapesilloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asistenciapesillo asistenciapesillo) {
        if (asistenciapesillo.getPagopesilloList() == null) {
            asistenciapesillo.setPagopesilloList(new ArrayList<Pagopesillo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacionpesillo idplanificacionpesillo = asistenciapesillo.getIdplanificacionpesillo();
            if (idplanificacionpesillo != null) {
                idplanificacionpesillo = em.getReference(idplanificacionpesillo.getClass(), idplanificacionpesillo.getIdplanificacionpesillo());
                asistenciapesillo.setIdplanificacionpesillo(idplanificacionpesillo);
            }
            List<Pagopesillo> attachedPagopesilloList = new ArrayList<Pagopesillo>();
            for (Pagopesillo pagopesilloListPagopesilloToAttach : asistenciapesillo.getPagopesilloList()) {
                pagopesilloListPagopesilloToAttach = em.getReference(pagopesilloListPagopesilloToAttach.getClass(), pagopesilloListPagopesilloToAttach.getIdpagopesillo());
                attachedPagopesilloList.add(pagopesilloListPagopesilloToAttach);
            }
            asistenciapesillo.setPagopesilloList(attachedPagopesilloList);
            em.persist(asistenciapesillo);
            if (idplanificacionpesillo != null) {
                idplanificacionpesillo.getAsistenciapesilloList().add(asistenciapesillo);
                idplanificacionpesillo = em.merge(idplanificacionpesillo);
            }
            for (Pagopesillo pagopesilloListPagopesillo : asistenciapesillo.getPagopesilloList()) {
                Asistenciapesillo oldIdasistenciapesilloOfPagopesilloListPagopesillo = pagopesilloListPagopesillo.getIdasistenciapesillo();
                pagopesilloListPagopesillo.setIdasistenciapesillo(asistenciapesillo);
                pagopesilloListPagopesillo = em.merge(pagopesilloListPagopesillo);
                if (oldIdasistenciapesilloOfPagopesilloListPagopesillo != null) {
                    oldIdasistenciapesilloOfPagopesilloListPagopesillo.getPagopesilloList().remove(pagopesilloListPagopesillo);
                    oldIdasistenciapesilloOfPagopesilloListPagopesillo = em.merge(oldIdasistenciapesilloOfPagopesilloListPagopesillo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asistenciapesillo asistenciapesillo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistenciapesillo persistentAsistenciapesillo = em.find(Asistenciapesillo.class, asistenciapesillo.getIdasistenciapesillo());
            Planificacionpesillo idplanificacionpesilloOld = persistentAsistenciapesillo.getIdplanificacionpesillo();
            Planificacionpesillo idplanificacionpesilloNew = asistenciapesillo.getIdplanificacionpesillo();
            List<Pagopesillo> pagopesilloListOld = persistentAsistenciapesillo.getPagopesilloList();
            List<Pagopesillo> pagopesilloListNew = asistenciapesillo.getPagopesilloList();
            if (idplanificacionpesilloNew != null) {
                idplanificacionpesilloNew = em.getReference(idplanificacionpesilloNew.getClass(), idplanificacionpesilloNew.getIdplanificacionpesillo());
                asistenciapesillo.setIdplanificacionpesillo(idplanificacionpesilloNew);
            }
            List<Pagopesillo> attachedPagopesilloListNew = new ArrayList<Pagopesillo>();
            for (Pagopesillo pagopesilloListNewPagopesilloToAttach : pagopesilloListNew) {
                pagopesilloListNewPagopesilloToAttach = em.getReference(pagopesilloListNewPagopesilloToAttach.getClass(), pagopesilloListNewPagopesilloToAttach.getIdpagopesillo());
                attachedPagopesilloListNew.add(pagopesilloListNewPagopesilloToAttach);
            }
            pagopesilloListNew = attachedPagopesilloListNew;
            asistenciapesillo.setPagopesilloList(pagopesilloListNew);
            asistenciapesillo = em.merge(asistenciapesillo);
            if (idplanificacionpesilloOld != null && !idplanificacionpesilloOld.equals(idplanificacionpesilloNew)) {
                idplanificacionpesilloOld.getAsistenciapesilloList().remove(asistenciapesillo);
                idplanificacionpesilloOld = em.merge(idplanificacionpesilloOld);
            }
            if (idplanificacionpesilloNew != null && !idplanificacionpesilloNew.equals(idplanificacionpesilloOld)) {
                idplanificacionpesilloNew.getAsistenciapesilloList().add(asistenciapesillo);
                idplanificacionpesilloNew = em.merge(idplanificacionpesilloNew);
            }
            for (Pagopesillo pagopesilloListOldPagopesillo : pagopesilloListOld) {
                if (!pagopesilloListNew.contains(pagopesilloListOldPagopesillo)) {
                    pagopesilloListOldPagopesillo.setIdasistenciapesillo(null);
                    pagopesilloListOldPagopesillo = em.merge(pagopesilloListOldPagopesillo);
                }
            }
            for (Pagopesillo pagopesilloListNewPagopesillo : pagopesilloListNew) {
                if (!pagopesilloListOld.contains(pagopesilloListNewPagopesillo)) {
                    Asistenciapesillo oldIdasistenciapesilloOfPagopesilloListNewPagopesillo = pagopesilloListNewPagopesillo.getIdasistenciapesillo();
                    pagopesilloListNewPagopesillo.setIdasistenciapesillo(asistenciapesillo);
                    pagopesilloListNewPagopesillo = em.merge(pagopesilloListNewPagopesillo);
                    if (oldIdasistenciapesilloOfPagopesilloListNewPagopesillo != null && !oldIdasistenciapesilloOfPagopesilloListNewPagopesillo.equals(asistenciapesillo)) {
                        oldIdasistenciapesilloOfPagopesilloListNewPagopesillo.getPagopesilloList().remove(pagopesilloListNewPagopesillo);
                        oldIdasistenciapesilloOfPagopesilloListNewPagopesillo = em.merge(oldIdasistenciapesilloOfPagopesilloListNewPagopesillo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asistenciapesillo.getIdasistenciapesillo();
                if (findAsistenciapesillo(id) == null) {
                    throw new NonexistentEntityException("The asistenciapesillo with id " + id + " no longer exists.");
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
            Asistenciapesillo asistenciapesillo;
            try {
                asistenciapesillo = em.getReference(Asistenciapesillo.class, id);
                asistenciapesillo.getIdasistenciapesillo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asistenciapesillo with id " + id + " no longer exists.", enfe);
            }
            Planificacionpesillo idplanificacionpesillo = asistenciapesillo.getIdplanificacionpesillo();
            if (idplanificacionpesillo != null) {
                idplanificacionpesillo.getAsistenciapesilloList().remove(asistenciapesillo);
                idplanificacionpesillo = em.merge(idplanificacionpesillo);
            }
            List<Pagopesillo> pagopesilloList = asistenciapesillo.getPagopesilloList();
            for (Pagopesillo pagopesilloListPagopesillo : pagopesilloList) {
                pagopesilloListPagopesillo.setIdasistenciapesillo(null);
                pagopesilloListPagopesillo = em.merge(pagopesilloListPagopesillo);
            }
            em.remove(asistenciapesillo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asistenciapesillo> findAsistenciapesilloEntities() {
        return findAsistenciapesilloEntities(true, -1, -1);
    }

    public List<Asistenciapesillo> findAsistenciapesilloEntities(int maxResults, int firstResult) {
        return findAsistenciapesilloEntities(false, maxResults, firstResult);
    }

    private List<Asistenciapesillo> findAsistenciapesilloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asistenciapesillo.class));
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

    public Asistenciapesillo findAsistenciapesillo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asistenciapesillo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsistenciapesilloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asistenciapesillo> rt = cq.from(Asistenciapesillo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
