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
import entidades.Planificacion;
import entidades.Medidor;
import entidades.Pagosasistencia;
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
public class AsistenciaJpaController implements Serializable {

    public AsistenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asistencia asistencia) {
        if (asistencia.getPagosasistenciaCollection() == null) {
            asistencia.setPagosasistenciaCollection(new ArrayList<Pagosasistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacion idplanificacion = asistencia.getIdplanificacion();
            if (idplanificacion != null) {
                idplanificacion = em.getReference(idplanificacion.getClass(), idplanificacion.getIdplanificacion());
                asistencia.setIdplanificacion(idplanificacion);
            }
            Medidor idmedidor = asistencia.getIdmedidor();
            if (idmedidor != null) {
                idmedidor = em.getReference(idmedidor.getClass(), idmedidor.getIdmedidor());
                asistencia.setIdmedidor(idmedidor);
            }
            Collection<Pagosasistencia> attachedPagosasistenciaCollection = new ArrayList<Pagosasistencia>();
            for (Pagosasistencia pagosasistenciaCollectionPagosasistenciaToAttach : asistencia.getPagosasistenciaCollection()) {
                pagosasistenciaCollectionPagosasistenciaToAttach = em.getReference(pagosasistenciaCollectionPagosasistenciaToAttach.getClass(), pagosasistenciaCollectionPagosasistenciaToAttach.getIdpagoasistencia());
                attachedPagosasistenciaCollection.add(pagosasistenciaCollectionPagosasistenciaToAttach);
            }
            asistencia.setPagosasistenciaCollection(attachedPagosasistenciaCollection);
            em.persist(asistencia);
            if (idplanificacion != null) {
                idplanificacion.getAsistenciaCollection().add(asistencia);
                idplanificacion = em.merge(idplanificacion);
            }
            if (idmedidor != null) {
                idmedidor.getAsistenciaCollection().add(asistencia);
                idmedidor = em.merge(idmedidor);
            }
            for (Pagosasistencia pagosasistenciaCollectionPagosasistencia : asistencia.getPagosasistenciaCollection()) {
                Asistencia oldIdasistenciaOfPagosasistenciaCollectionPagosasistencia = pagosasistenciaCollectionPagosasistencia.getIdasistencia();
                pagosasistenciaCollectionPagosasistencia.setIdasistencia(asistencia);
                pagosasistenciaCollectionPagosasistencia = em.merge(pagosasistenciaCollectionPagosasistencia);
                if (oldIdasistenciaOfPagosasistenciaCollectionPagosasistencia != null) {
                    oldIdasistenciaOfPagosasistenciaCollectionPagosasistencia.getPagosasistenciaCollection().remove(pagosasistenciaCollectionPagosasistencia);
                    oldIdasistenciaOfPagosasistenciaCollectionPagosasistencia = em.merge(oldIdasistenciaOfPagosasistenciaCollectionPagosasistencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asistencia asistencia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistencia persistentAsistencia = em.find(Asistencia.class, asistencia.getIdasistencia());
            Planificacion idplanificacionOld = persistentAsistencia.getIdplanificacion();
            Planificacion idplanificacionNew = asistencia.getIdplanificacion();
            Medidor idmedidorOld = persistentAsistencia.getIdmedidor();
            Medidor idmedidorNew = asistencia.getIdmedidor();
            Collection<Pagosasistencia> pagosasistenciaCollectionOld = persistentAsistencia.getPagosasistenciaCollection();
            Collection<Pagosasistencia> pagosasistenciaCollectionNew = asistencia.getPagosasistenciaCollection();
            List<String> illegalOrphanMessages = null;
            for (Pagosasistencia pagosasistenciaCollectionOldPagosasistencia : pagosasistenciaCollectionOld) {
                if (!pagosasistenciaCollectionNew.contains(pagosasistenciaCollectionOldPagosasistencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagosasistencia " + pagosasistenciaCollectionOldPagosasistencia + " since its idasistencia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idplanificacionNew != null) {
                idplanificacionNew = em.getReference(idplanificacionNew.getClass(), idplanificacionNew.getIdplanificacion());
                asistencia.setIdplanificacion(idplanificacionNew);
            }
            if (idmedidorNew != null) {
                idmedidorNew = em.getReference(idmedidorNew.getClass(), idmedidorNew.getIdmedidor());
                asistencia.setIdmedidor(idmedidorNew);
            }
            Collection<Pagosasistencia> attachedPagosasistenciaCollectionNew = new ArrayList<Pagosasistencia>();
            for (Pagosasistencia pagosasistenciaCollectionNewPagosasistenciaToAttach : pagosasistenciaCollectionNew) {
                pagosasistenciaCollectionNewPagosasistenciaToAttach = em.getReference(pagosasistenciaCollectionNewPagosasistenciaToAttach.getClass(), pagosasistenciaCollectionNewPagosasistenciaToAttach.getIdpagoasistencia());
                attachedPagosasistenciaCollectionNew.add(pagosasistenciaCollectionNewPagosasistenciaToAttach);
            }
            pagosasistenciaCollectionNew = attachedPagosasistenciaCollectionNew;
            asistencia.setPagosasistenciaCollection(pagosasistenciaCollectionNew);
            asistencia = em.merge(asistencia);
            if (idplanificacionOld != null && !idplanificacionOld.equals(idplanificacionNew)) {
                idplanificacionOld.getAsistenciaCollection().remove(asistencia);
                idplanificacionOld = em.merge(idplanificacionOld);
            }
            if (idplanificacionNew != null && !idplanificacionNew.equals(idplanificacionOld)) {
                idplanificacionNew.getAsistenciaCollection().add(asistencia);
                idplanificacionNew = em.merge(idplanificacionNew);
            }
            if (idmedidorOld != null && !idmedidorOld.equals(idmedidorNew)) {
                idmedidorOld.getAsistenciaCollection().remove(asistencia);
                idmedidorOld = em.merge(idmedidorOld);
            }
            if (idmedidorNew != null && !idmedidorNew.equals(idmedidorOld)) {
                idmedidorNew.getAsistenciaCollection().add(asistencia);
                idmedidorNew = em.merge(idmedidorNew);
            }
            for (Pagosasistencia pagosasistenciaCollectionNewPagosasistencia : pagosasistenciaCollectionNew) {
                if (!pagosasistenciaCollectionOld.contains(pagosasistenciaCollectionNewPagosasistencia)) {
                    Asistencia oldIdasistenciaOfPagosasistenciaCollectionNewPagosasistencia = pagosasistenciaCollectionNewPagosasistencia.getIdasistencia();
                    pagosasistenciaCollectionNewPagosasistencia.setIdasistencia(asistencia);
                    pagosasistenciaCollectionNewPagosasistencia = em.merge(pagosasistenciaCollectionNewPagosasistencia);
                    if (oldIdasistenciaOfPagosasistenciaCollectionNewPagosasistencia != null && !oldIdasistenciaOfPagosasistenciaCollectionNewPagosasistencia.equals(asistencia)) {
                        oldIdasistenciaOfPagosasistenciaCollectionNewPagosasistencia.getPagosasistenciaCollection().remove(pagosasistenciaCollectionNewPagosasistencia);
                        oldIdasistenciaOfPagosasistenciaCollectionNewPagosasistencia = em.merge(oldIdasistenciaOfPagosasistenciaCollectionNewPagosasistencia);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Pagosasistencia> pagosasistenciaCollectionOrphanCheck = asistencia.getPagosasistenciaCollection();
            for (Pagosasistencia pagosasistenciaCollectionOrphanCheckPagosasistencia : pagosasistenciaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asistencia (" + asistencia + ") cannot be destroyed since the Pagosasistencia " + pagosasistenciaCollectionOrphanCheckPagosasistencia + " in its pagosasistenciaCollection field has a non-nullable idasistencia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Planificacion idplanificacion = asistencia.getIdplanificacion();
            if (idplanificacion != null) {
                idplanificacion.getAsistenciaCollection().remove(asistencia);
                idplanificacion = em.merge(idplanificacion);
            }
            Medidor idmedidor = asistencia.getIdmedidor();
            if (idmedidor != null) {
                idmedidor.getAsistenciaCollection().remove(asistencia);
                idmedidor = em.merge(idmedidor);
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
