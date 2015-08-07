/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Asistencia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Medidor;
import entidades.Planificacion;
import entidades.Pagosasistencia;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JC-PC
 */
public class AsistenciaJpaController implements Serializable {

    public AsistenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asistencia asistencia) {
        if (asistencia.getPagosasistenciaList() == null) {
            asistencia.setPagosasistenciaList(new ArrayList<Pagosasistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidor idmedidor = asistencia.getIdmedidor();
            if (idmedidor != null) {
                idmedidor = em.getReference(idmedidor.getClass(), idmedidor.getIdmedidor());
                asistencia.setIdmedidor(idmedidor);
            }
            Planificacion idplanificacion = asistencia.getIdplanificacion();
            if (idplanificacion != null) {
                idplanificacion = em.getReference(idplanificacion.getClass(), idplanificacion.getIdplanificacion());
                asistencia.setIdplanificacion(idplanificacion);
            }
            List<Pagosasistencia> attachedPagosasistenciaList = new ArrayList<Pagosasistencia>();
            for (Pagosasistencia pagosasistenciaListPagosasistenciaToAttach : asistencia.getPagosasistenciaList()) {
                pagosasistenciaListPagosasistenciaToAttach = em.getReference(pagosasistenciaListPagosasistenciaToAttach.getClass(), pagosasistenciaListPagosasistenciaToAttach.getIdpagoasistencia());
                attachedPagosasistenciaList.add(pagosasistenciaListPagosasistenciaToAttach);
            }
            asistencia.setPagosasistenciaList(attachedPagosasistenciaList);
            em.persist(asistencia);
            if (idmedidor != null) {
                idmedidor.getAsistenciaList().add(asistencia);
                idmedidor = em.merge(idmedidor);
            }
            if (idplanificacion != null) {
                idplanificacion.getAsistenciaList().add(asistencia);
                idplanificacion = em.merge(idplanificacion);
            }
            for (Pagosasistencia pagosasistenciaListPagosasistencia : asistencia.getPagosasistenciaList()) {
                Asistencia oldIdasistenciaOfPagosasistenciaListPagosasistencia = pagosasistenciaListPagosasistencia.getIdasistencia();
                pagosasistenciaListPagosasistencia.setIdasistencia(asistencia);
                pagosasistenciaListPagosasistencia = em.merge(pagosasistenciaListPagosasistencia);
                if (oldIdasistenciaOfPagosasistenciaListPagosasistencia != null) {
                    oldIdasistenciaOfPagosasistenciaListPagosasistencia.getPagosasistenciaList().remove(pagosasistenciaListPagosasistencia);
                    oldIdasistenciaOfPagosasistenciaListPagosasistencia = em.merge(oldIdasistenciaOfPagosasistenciaListPagosasistencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asistencia asistencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistencia persistentAsistencia = em.find(Asistencia.class, asistencia.getIdasistencia());
            Medidor idmedidorOld = persistentAsistencia.getIdmedidor();
            Medidor idmedidorNew = asistencia.getIdmedidor();
            Planificacion idplanificacionOld = persistentAsistencia.getIdplanificacion();
            Planificacion idplanificacionNew = asistencia.getIdplanificacion();
            List<Pagosasistencia> pagosasistenciaListOld = persistentAsistencia.getPagosasistenciaList();
            List<Pagosasistencia> pagosasistenciaListNew = asistencia.getPagosasistenciaList();
            if (idmedidorNew != null) {
                idmedidorNew = em.getReference(idmedidorNew.getClass(), idmedidorNew.getIdmedidor());
                asistencia.setIdmedidor(idmedidorNew);
            }
            if (idplanificacionNew != null) {
                idplanificacionNew = em.getReference(idplanificacionNew.getClass(), idplanificacionNew.getIdplanificacion());
                asistencia.setIdplanificacion(idplanificacionNew);
            }
            List<Pagosasistencia> attachedPagosasistenciaListNew = new ArrayList<Pagosasistencia>();
            for (Pagosasistencia pagosasistenciaListNewPagosasistenciaToAttach : pagosasistenciaListNew) {
                pagosasistenciaListNewPagosasistenciaToAttach = em.getReference(pagosasistenciaListNewPagosasistenciaToAttach.getClass(), pagosasistenciaListNewPagosasistenciaToAttach.getIdpagoasistencia());
                attachedPagosasistenciaListNew.add(pagosasistenciaListNewPagosasistenciaToAttach);
            }
            pagosasistenciaListNew = attachedPagosasistenciaListNew;
            asistencia.setPagosasistenciaList(pagosasistenciaListNew);
            asistencia = em.merge(asistencia);
            if (idmedidorOld != null && !idmedidorOld.equals(idmedidorNew)) {
                idmedidorOld.getAsistenciaList().remove(asistencia);
                idmedidorOld = em.merge(idmedidorOld);
            }
            if (idmedidorNew != null && !idmedidorNew.equals(idmedidorOld)) {
                idmedidorNew.getAsistenciaList().add(asistencia);
                idmedidorNew = em.merge(idmedidorNew);
            }
            if (idplanificacionOld != null && !idplanificacionOld.equals(idplanificacionNew)) {
                idplanificacionOld.getAsistenciaList().remove(asistencia);
                idplanificacionOld = em.merge(idplanificacionOld);
            }
            if (idplanificacionNew != null && !idplanificacionNew.equals(idplanificacionOld)) {
                idplanificacionNew.getAsistenciaList().add(asistencia);
                idplanificacionNew = em.merge(idplanificacionNew);
            }
            for (Pagosasistencia pagosasistenciaListOldPagosasistencia : pagosasistenciaListOld) {
                if (!pagosasistenciaListNew.contains(pagosasistenciaListOldPagosasistencia)) {
                    pagosasistenciaListOldPagosasistencia.setIdasistencia(null);
                    pagosasistenciaListOldPagosasistencia = em.merge(pagosasistenciaListOldPagosasistencia);
                }
            }
            for (Pagosasistencia pagosasistenciaListNewPagosasistencia : pagosasistenciaListNew) {
                if (!pagosasistenciaListOld.contains(pagosasistenciaListNewPagosasistencia)) {
                    Asistencia oldIdasistenciaOfPagosasistenciaListNewPagosasistencia = pagosasistenciaListNewPagosasistencia.getIdasistencia();
                    pagosasistenciaListNewPagosasistencia.setIdasistencia(asistencia);
                    pagosasistenciaListNewPagosasistencia = em.merge(pagosasistenciaListNewPagosasistencia);
                    if (oldIdasistenciaOfPagosasistenciaListNewPagosasistencia != null && !oldIdasistenciaOfPagosasistenciaListNewPagosasistencia.equals(asistencia)) {
                        oldIdasistenciaOfPagosasistenciaListNewPagosasistencia.getPagosasistenciaList().remove(pagosasistenciaListNewPagosasistencia);
                        oldIdasistenciaOfPagosasistenciaListNewPagosasistencia = em.merge(oldIdasistenciaOfPagosasistenciaListNewPagosasistencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asistencia.getIdasistencia();
                if (findAsistencia(id) == null) {
                    throw new NonexistentEntityException("The asistencia with id " + id + " no longer exists.");
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
            Asistencia asistencia;
            try {
                asistencia = em.getReference(Asistencia.class, id);
                asistencia.getIdasistencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asistencia with id " + id + " no longer exists.", enfe);
            }
            Medidor idmedidor = asistencia.getIdmedidor();
            if (idmedidor != null) {
                idmedidor.getAsistenciaList().remove(asistencia);
                idmedidor = em.merge(idmedidor);
            }
            Planificacion idplanificacion = asistencia.getIdplanificacion();
            if (idplanificacion != null) {
                idplanificacion.getAsistenciaList().remove(asistencia);
                idplanificacion = em.merge(idplanificacion);
            }
            List<Pagosasistencia> pagosasistenciaList = asistencia.getPagosasistenciaList();
            for (Pagosasistencia pagosasistenciaListPagosasistencia : pagosasistenciaList) {
                pagosasistenciaListPagosasistencia.setIdasistencia(null);
                pagosasistenciaListPagosasistencia = em.merge(pagosasistenciaListPagosasistencia);
            }
            em.remove(asistencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asistencia> findAsistenciaEntities() {
        return findAsistenciaEntities(true, -1, -1);
    }

    public List<Asistencia> findAsistenciaEntities(int maxResults, int firstResult) {
        return findAsistenciaEntities(false, maxResults, firstResult);
    }

    private List<Asistencia> findAsistenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asistencia.class));
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

    public Asistencia findAsistencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asistencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsistenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asistencia> rt = cq.from(Asistencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
