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
import entidades.Aguaganaderia;
import entidades.Detallefacturaganaderia;
import entidades.Tarifasganaderia;
import entidades.Facturasganaderia;
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
public class DetallefacturaganaderiaJpaController implements Serializable {

    public DetallefacturaganaderiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefacturaganaderia detallefacturaganaderia) {
        if (detallefacturaganaderia.getFacturasganaderiaCollection() == null) {
            detallefacturaganaderia.setFacturasganaderiaCollection(new ArrayList<Facturasganaderia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aguaganaderia idaguaganaderia = detallefacturaganaderia.getIdaguaganaderia();
            if (idaguaganaderia != null) {
                idaguaganaderia = em.getReference(idaguaganaderia.getClass(), idaguaganaderia.getIdaguaganaderia());
                detallefacturaganaderia.setIdaguaganaderia(idaguaganaderia);
            }
            Tarifasganaderia idtarifasganaderia = detallefacturaganaderia.getIdtarifasganaderia();
            if (idtarifasganaderia != null) {
                idtarifasganaderia = em.getReference(idtarifasganaderia.getClass(), idtarifasganaderia.getIdtarifasganaderia());
                detallefacturaganaderia.setIdtarifasganaderia(idtarifasganaderia);
            }
            Collection<Facturasganaderia> attachedFacturasganaderiaCollection = new ArrayList<Facturasganaderia>();
            for (Facturasganaderia facturasganaderiaCollectionFacturasganaderiaToAttach : detallefacturaganaderia.getFacturasganaderiaCollection()) {
                facturasganaderiaCollectionFacturasganaderiaToAttach = em.getReference(facturasganaderiaCollectionFacturasganaderiaToAttach.getClass(), facturasganaderiaCollectionFacturasganaderiaToAttach.getIdfacturasganaderia());
                attachedFacturasganaderiaCollection.add(facturasganaderiaCollectionFacturasganaderiaToAttach);
            }
            detallefacturaganaderia.setFacturasganaderiaCollection(attachedFacturasganaderiaCollection);
            em.persist(detallefacturaganaderia);
            if (idaguaganaderia != null) {
                idaguaganaderia.getDetallefacturaganaderiaCollection().add(detallefacturaganaderia);
                idaguaganaderia = em.merge(idaguaganaderia);
            }
            if (idtarifasganaderia != null) {
                idtarifasganaderia.getDetallefacturaganaderiaCollection().add(detallefacturaganaderia);
                idtarifasganaderia = em.merge(idtarifasganaderia);
            }
            for (Facturasganaderia facturasganaderiaCollectionFacturasganaderia : detallefacturaganaderia.getFacturasganaderiaCollection()) {
                Detallefacturaganaderia oldIddetallefacganaderiaOfFacturasganaderiaCollectionFacturasganaderia = facturasganaderiaCollectionFacturasganaderia.getIddetallefacganaderia();
                facturasganaderiaCollectionFacturasganaderia.setIddetallefacganaderia(detallefacturaganaderia);
                facturasganaderiaCollectionFacturasganaderia = em.merge(facturasganaderiaCollectionFacturasganaderia);
                if (oldIddetallefacganaderiaOfFacturasganaderiaCollectionFacturasganaderia != null) {
                    oldIddetallefacganaderiaOfFacturasganaderiaCollectionFacturasganaderia.getFacturasganaderiaCollection().remove(facturasganaderiaCollectionFacturasganaderia);
                    oldIddetallefacganaderiaOfFacturasganaderiaCollectionFacturasganaderia = em.merge(oldIddetallefacganaderiaOfFacturasganaderiaCollectionFacturasganaderia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefacturaganaderia detallefacturaganaderia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefacturaganaderia persistentDetallefacturaganaderia = em.find(Detallefacturaganaderia.class, detallefacturaganaderia.getIddetallefacganaderia());
            Aguaganaderia idaguaganaderiaOld = persistentDetallefacturaganaderia.getIdaguaganaderia();
            Aguaganaderia idaguaganaderiaNew = detallefacturaganaderia.getIdaguaganaderia();
            Tarifasganaderia idtarifasganaderiaOld = persistentDetallefacturaganaderia.getIdtarifasganaderia();
            Tarifasganaderia idtarifasganaderiaNew = detallefacturaganaderia.getIdtarifasganaderia();
            Collection<Facturasganaderia> facturasganaderiaCollectionOld = persistentDetallefacturaganaderia.getFacturasganaderiaCollection();
            Collection<Facturasganaderia> facturasganaderiaCollectionNew = detallefacturaganaderia.getFacturasganaderiaCollection();
            List<String> illegalOrphanMessages = null;
            for (Facturasganaderia facturasganaderiaCollectionOldFacturasganaderia : facturasganaderiaCollectionOld) {
                if (!facturasganaderiaCollectionNew.contains(facturasganaderiaCollectionOldFacturasganaderia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturasganaderia " + facturasganaderiaCollectionOldFacturasganaderia + " since its iddetallefacganaderia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idaguaganaderiaNew != null) {
                idaguaganaderiaNew = em.getReference(idaguaganaderiaNew.getClass(), idaguaganaderiaNew.getIdaguaganaderia());
                detallefacturaganaderia.setIdaguaganaderia(idaguaganaderiaNew);
            }
            if (idtarifasganaderiaNew != null) {
                idtarifasganaderiaNew = em.getReference(idtarifasganaderiaNew.getClass(), idtarifasganaderiaNew.getIdtarifasganaderia());
                detallefacturaganaderia.setIdtarifasganaderia(idtarifasganaderiaNew);
            }
            Collection<Facturasganaderia> attachedFacturasganaderiaCollectionNew = new ArrayList<Facturasganaderia>();
            for (Facturasganaderia facturasganaderiaCollectionNewFacturasganaderiaToAttach : facturasganaderiaCollectionNew) {
                facturasganaderiaCollectionNewFacturasganaderiaToAttach = em.getReference(facturasganaderiaCollectionNewFacturasganaderiaToAttach.getClass(), facturasganaderiaCollectionNewFacturasganaderiaToAttach.getIdfacturasganaderia());
                attachedFacturasganaderiaCollectionNew.add(facturasganaderiaCollectionNewFacturasganaderiaToAttach);
            }
            facturasganaderiaCollectionNew = attachedFacturasganaderiaCollectionNew;
            detallefacturaganaderia.setFacturasganaderiaCollection(facturasganaderiaCollectionNew);
            detallefacturaganaderia = em.merge(detallefacturaganaderia);
            if (idaguaganaderiaOld != null && !idaguaganaderiaOld.equals(idaguaganaderiaNew)) {
                idaguaganaderiaOld.getDetallefacturaganaderiaCollection().remove(detallefacturaganaderia);
                idaguaganaderiaOld = em.merge(idaguaganaderiaOld);
            }
            if (idaguaganaderiaNew != null && !idaguaganaderiaNew.equals(idaguaganaderiaOld)) {
                idaguaganaderiaNew.getDetallefacturaganaderiaCollection().add(detallefacturaganaderia);
                idaguaganaderiaNew = em.merge(idaguaganaderiaNew);
            }
            if (idtarifasganaderiaOld != null && !idtarifasganaderiaOld.equals(idtarifasganaderiaNew)) {
                idtarifasganaderiaOld.getDetallefacturaganaderiaCollection().remove(detallefacturaganaderia);
                idtarifasganaderiaOld = em.merge(idtarifasganaderiaOld);
            }
            if (idtarifasganaderiaNew != null && !idtarifasganaderiaNew.equals(idtarifasganaderiaOld)) {
                idtarifasganaderiaNew.getDetallefacturaganaderiaCollection().add(detallefacturaganaderia);
                idtarifasganaderiaNew = em.merge(idtarifasganaderiaNew);
            }
            for (Facturasganaderia facturasganaderiaCollectionNewFacturasganaderia : facturasganaderiaCollectionNew) {
                if (!facturasganaderiaCollectionOld.contains(facturasganaderiaCollectionNewFacturasganaderia)) {
                    Detallefacturaganaderia oldIddetallefacganaderiaOfFacturasganaderiaCollectionNewFacturasganaderia = facturasganaderiaCollectionNewFacturasganaderia.getIddetallefacganaderia();
                    facturasganaderiaCollectionNewFacturasganaderia.setIddetallefacganaderia(detallefacturaganaderia);
                    facturasganaderiaCollectionNewFacturasganaderia = em.merge(facturasganaderiaCollectionNewFacturasganaderia);
                    if (oldIddetallefacganaderiaOfFacturasganaderiaCollectionNewFacturasganaderia != null && !oldIddetallefacganaderiaOfFacturasganaderiaCollectionNewFacturasganaderia.equals(detallefacturaganaderia)) {
                        oldIddetallefacganaderiaOfFacturasganaderiaCollectionNewFacturasganaderia.getFacturasganaderiaCollection().remove(facturasganaderiaCollectionNewFacturasganaderia);
                        oldIddetallefacganaderiaOfFacturasganaderiaCollectionNewFacturasganaderia = em.merge(oldIddetallefacganaderiaOfFacturasganaderiaCollectionNewFacturasganaderia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallefacturaganaderia.getIddetallefacganaderia();
                if (findDetallefacturaganaderia(id) == null) {
                    throw new NonexistentEntityException("The detallefacturaganaderia with id " + id + " no longer exists.");
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
            Detallefacturaganaderia detallefacturaganaderia;
            try {
                detallefacturaganaderia = em.getReference(Detallefacturaganaderia.class, id);
                detallefacturaganaderia.getIddetallefacganaderia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefacturaganaderia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Facturasganaderia> facturasganaderiaCollectionOrphanCheck = detallefacturaganaderia.getFacturasganaderiaCollection();
            for (Facturasganaderia facturasganaderiaCollectionOrphanCheckFacturasganaderia : facturasganaderiaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detallefacturaganaderia (" + detallefacturaganaderia + ") cannot be destroyed since the Facturasganaderia " + facturasganaderiaCollectionOrphanCheckFacturasganaderia + " in its facturasganaderiaCollection field has a non-nullable iddetallefacganaderia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Aguaganaderia idaguaganaderia = detallefacturaganaderia.getIdaguaganaderia();
            if (idaguaganaderia != null) {
                idaguaganaderia.getDetallefacturaganaderiaCollection().remove(detallefacturaganaderia);
                idaguaganaderia = em.merge(idaguaganaderia);
            }
            Tarifasganaderia idtarifasganaderia = detallefacturaganaderia.getIdtarifasganaderia();
            if (idtarifasganaderia != null) {
                idtarifasganaderia.getDetallefacturaganaderiaCollection().remove(detallefacturaganaderia);
                idtarifasganaderia = em.merge(idtarifasganaderia);
            }
            em.remove(detallefacturaganaderia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefacturaganaderia> findDetallefacturaganaderiaEntities() {
        return findDetallefacturaganaderiaEntities(true, -1, -1);
    }

    public List<Detallefacturaganaderia> findDetallefacturaganaderiaEntities(int maxResults, int firstResult) {
        return findDetallefacturaganaderiaEntities(false, maxResults, firstResult);
    }

    private List<Detallefacturaganaderia> findDetallefacturaganaderiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefacturaganaderia.class));
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

    public Detallefacturaganaderia findDetallefacturaganaderia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefacturaganaderia.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturaganaderiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefacturaganaderia> rt = cq.from(Detallefacturaganaderia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
