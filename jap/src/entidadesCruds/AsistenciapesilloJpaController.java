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
import entidades.Medidor;
import entidades.Planificacionpesillo;
import entidades.Pagopesillo;
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
public class AsistenciapesilloJpaController implements Serializable {

    public AsistenciapesilloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asistenciapesillo asistenciapesillo) {
        if (asistenciapesillo.getPagopesilloCollection() == null) {
            asistenciapesillo.setPagopesilloCollection(new ArrayList<Pagopesillo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidor idmedidor = asistenciapesillo.getIdmedidor();
            if (idmedidor != null) {
                idmedidor = em.getReference(idmedidor.getClass(), idmedidor.getIdmedidor());
                asistenciapesillo.setIdmedidor(idmedidor);
            }
            Planificacionpesillo idplanificacionpesillo = asistenciapesillo.getIdplanificacionpesillo();
            if (idplanificacionpesillo != null) {
                idplanificacionpesillo = em.getReference(idplanificacionpesillo.getClass(), idplanificacionpesillo.getIdplanificacionpesillo());
                asistenciapesillo.setIdplanificacionpesillo(idplanificacionpesillo);
            }
            Collection<Pagopesillo> attachedPagopesilloCollection = new ArrayList<Pagopesillo>();
            for (Pagopesillo pagopesilloCollectionPagopesilloToAttach : asistenciapesillo.getPagopesilloCollection()) {
                pagopesilloCollectionPagopesilloToAttach = em.getReference(pagopesilloCollectionPagopesilloToAttach.getClass(), pagopesilloCollectionPagopesilloToAttach.getIdpagopesillo());
                attachedPagopesilloCollection.add(pagopesilloCollectionPagopesilloToAttach);
            }
            asistenciapesillo.setPagopesilloCollection(attachedPagopesilloCollection);
            em.persist(asistenciapesillo);
            if (idmedidor != null) {
                idmedidor.getAsistenciapesilloCollection().add(asistenciapesillo);
                idmedidor = em.merge(idmedidor);
            }
            if (idplanificacionpesillo != null) {
                idplanificacionpesillo.getAsistenciapesilloCollection().add(asistenciapesillo);
                idplanificacionpesillo = em.merge(idplanificacionpesillo);
            }
            for (Pagopesillo pagopesilloCollectionPagopesillo : asistenciapesillo.getPagopesilloCollection()) {
                Asistenciapesillo oldIdasistenciapesilloOfPagopesilloCollectionPagopesillo = pagopesilloCollectionPagopesillo.getIdasistenciapesillo();
                pagopesilloCollectionPagopesillo.setIdasistenciapesillo(asistenciapesillo);
                pagopesilloCollectionPagopesillo = em.merge(pagopesilloCollectionPagopesillo);
                if (oldIdasistenciapesilloOfPagopesilloCollectionPagopesillo != null) {
                    oldIdasistenciapesilloOfPagopesilloCollectionPagopesillo.getPagopesilloCollection().remove(pagopesilloCollectionPagopesillo);
                    oldIdasistenciapesilloOfPagopesilloCollectionPagopesillo = em.merge(oldIdasistenciapesilloOfPagopesilloCollectionPagopesillo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asistenciapesillo asistenciapesillo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistenciapesillo persistentAsistenciapesillo = em.find(Asistenciapesillo.class, asistenciapesillo.getIdasistenciapesillo());
            Medidor idmedidorOld = persistentAsistenciapesillo.getIdmedidor();
            Medidor idmedidorNew = asistenciapesillo.getIdmedidor();
            Planificacionpesillo idplanificacionpesilloOld = persistentAsistenciapesillo.getIdplanificacionpesillo();
            Planificacionpesillo idplanificacionpesilloNew = asistenciapesillo.getIdplanificacionpesillo();
            Collection<Pagopesillo> pagopesilloCollectionOld = persistentAsistenciapesillo.getPagopesilloCollection();
            Collection<Pagopesillo> pagopesilloCollectionNew = asistenciapesillo.getPagopesilloCollection();
            List<String> illegalOrphanMessages = null;
            for (Pagopesillo pagopesilloCollectionOldPagopesillo : pagopesilloCollectionOld) {
                if (!pagopesilloCollectionNew.contains(pagopesilloCollectionOldPagopesillo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagopesillo " + pagopesilloCollectionOldPagopesillo + " since its idasistenciapesillo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idmedidorNew != null) {
                idmedidorNew = em.getReference(idmedidorNew.getClass(), idmedidorNew.getIdmedidor());
                asistenciapesillo.setIdmedidor(idmedidorNew);
            }
            if (idplanificacionpesilloNew != null) {
                idplanificacionpesilloNew = em.getReference(idplanificacionpesilloNew.getClass(), idplanificacionpesilloNew.getIdplanificacionpesillo());
                asistenciapesillo.setIdplanificacionpesillo(idplanificacionpesilloNew);
            }
            Collection<Pagopesillo> attachedPagopesilloCollectionNew = new ArrayList<Pagopesillo>();
            for (Pagopesillo pagopesilloCollectionNewPagopesilloToAttach : pagopesilloCollectionNew) {
                pagopesilloCollectionNewPagopesilloToAttach = em.getReference(pagopesilloCollectionNewPagopesilloToAttach.getClass(), pagopesilloCollectionNewPagopesilloToAttach.getIdpagopesillo());
                attachedPagopesilloCollectionNew.add(pagopesilloCollectionNewPagopesilloToAttach);
            }
            pagopesilloCollectionNew = attachedPagopesilloCollectionNew;
            asistenciapesillo.setPagopesilloCollection(pagopesilloCollectionNew);
            asistenciapesillo = em.merge(asistenciapesillo);
            if (idmedidorOld != null && !idmedidorOld.equals(idmedidorNew)) {
                idmedidorOld.getAsistenciapesilloCollection().remove(asistenciapesillo);
                idmedidorOld = em.merge(idmedidorOld);
            }
            if (idmedidorNew != null && !idmedidorNew.equals(idmedidorOld)) {
                idmedidorNew.getAsistenciapesilloCollection().add(asistenciapesillo);
                idmedidorNew = em.merge(idmedidorNew);
            }
            if (idplanificacionpesilloOld != null && !idplanificacionpesilloOld.equals(idplanificacionpesilloNew)) {
                idplanificacionpesilloOld.getAsistenciapesilloCollection().remove(asistenciapesillo);
                idplanificacionpesilloOld = em.merge(idplanificacionpesilloOld);
            }
            if (idplanificacionpesilloNew != null && !idplanificacionpesilloNew.equals(idplanificacionpesilloOld)) {
                idplanificacionpesilloNew.getAsistenciapesilloCollection().add(asistenciapesillo);
                idplanificacionpesilloNew = em.merge(idplanificacionpesilloNew);
            }
            for (Pagopesillo pagopesilloCollectionNewPagopesillo : pagopesilloCollectionNew) {
                if (!pagopesilloCollectionOld.contains(pagopesilloCollectionNewPagopesillo)) {
                    Asistenciapesillo oldIdasistenciapesilloOfPagopesilloCollectionNewPagopesillo = pagopesilloCollectionNewPagopesillo.getIdasistenciapesillo();
                    pagopesilloCollectionNewPagopesillo.setIdasistenciapesillo(asistenciapesillo);
                    pagopesilloCollectionNewPagopesillo = em.merge(pagopesilloCollectionNewPagopesillo);
                    if (oldIdasistenciapesilloOfPagopesilloCollectionNewPagopesillo != null && !oldIdasistenciapesilloOfPagopesilloCollectionNewPagopesillo.equals(asistenciapesillo)) {
                        oldIdasistenciapesilloOfPagopesilloCollectionNewPagopesillo.getPagopesilloCollection().remove(pagopesilloCollectionNewPagopesillo);
                        oldIdasistenciapesilloOfPagopesilloCollectionNewPagopesillo = em.merge(oldIdasistenciapesilloOfPagopesilloCollectionNewPagopesillo);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Pagopesillo> pagopesilloCollectionOrphanCheck = asistenciapesillo.getPagopesilloCollection();
            for (Pagopesillo pagopesilloCollectionOrphanCheckPagopesillo : pagopesilloCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asistenciapesillo (" + asistenciapesillo + ") cannot be destroyed since the Pagopesillo " + pagopesilloCollectionOrphanCheckPagopesillo + " in its pagopesilloCollection field has a non-nullable idasistenciapesillo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Medidor idmedidor = asistenciapesillo.getIdmedidor();
            if (idmedidor != null) {
                idmedidor.getAsistenciapesilloCollection().remove(asistenciapesillo);
                idmedidor = em.merge(idmedidor);
            }
            Planificacionpesillo idplanificacionpesillo = asistenciapesillo.getIdplanificacionpesillo();
            if (idplanificacionpesillo != null) {
                idplanificacionpesillo.getAsistenciapesilloCollection().remove(asistenciapesillo);
                idplanificacionpesillo = em.merge(idplanificacionpesillo);
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
