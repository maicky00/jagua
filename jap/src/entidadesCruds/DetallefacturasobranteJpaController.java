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
import entidades.Aguasobrante;
import entidades.Detallefacturasobrante;
import entidades.Tarifassobrante;
import entidades.Facturassobrante;
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
public class DetallefacturasobranteJpaController implements Serializable {

    public DetallefacturasobranteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefacturasobrante detallefacturasobrante) {
        if (detallefacturasobrante.getFacturassobranteCollection() == null) {
            detallefacturasobrante.setFacturassobranteCollection(new ArrayList<Facturassobrante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aguasobrante idaguasobrante = detallefacturasobrante.getIdaguasobrante();
            if (idaguasobrante != null) {
                idaguasobrante = em.getReference(idaguasobrante.getClass(), idaguasobrante.getIdaguasobrante());
                detallefacturasobrante.setIdaguasobrante(idaguasobrante);
            }
            Tarifassobrante idtarifassobrante = detallefacturasobrante.getIdtarifassobrante();
            if (idtarifassobrante != null) {
                idtarifassobrante = em.getReference(idtarifassobrante.getClass(), idtarifassobrante.getIdtarifassobrante());
                detallefacturasobrante.setIdtarifassobrante(idtarifassobrante);
            }
            Collection<Facturassobrante> attachedFacturassobranteCollection = new ArrayList<Facturassobrante>();
            for (Facturassobrante facturassobranteCollectionFacturassobranteToAttach : detallefacturasobrante.getFacturassobranteCollection()) {
                facturassobranteCollectionFacturassobranteToAttach = em.getReference(facturassobranteCollectionFacturassobranteToAttach.getClass(), facturassobranteCollectionFacturassobranteToAttach.getIdfacturasobrante());
                attachedFacturassobranteCollection.add(facturassobranteCollectionFacturassobranteToAttach);
            }
            detallefacturasobrante.setFacturassobranteCollection(attachedFacturassobranteCollection);
            em.persist(detallefacturasobrante);
            if (idaguasobrante != null) {
                idaguasobrante.getDetallefacturasobranteCollection().add(detallefacturasobrante);
                idaguasobrante = em.merge(idaguasobrante);
            }
            if (idtarifassobrante != null) {
                idtarifassobrante.getDetallefacturasobranteCollection().add(detallefacturasobrante);
                idtarifassobrante = em.merge(idtarifassobrante);
            }
            for (Facturassobrante facturassobranteCollectionFacturassobrante : detallefacturasobrante.getFacturassobranteCollection()) {
                Detallefacturasobrante oldIddetallefacsobranteOfFacturassobranteCollectionFacturassobrante = facturassobranteCollectionFacturassobrante.getIddetallefacsobrante();
                facturassobranteCollectionFacturassobrante.setIddetallefacsobrante(detallefacturasobrante);
                facturassobranteCollectionFacturassobrante = em.merge(facturassobranteCollectionFacturassobrante);
                if (oldIddetallefacsobranteOfFacturassobranteCollectionFacturassobrante != null) {
                    oldIddetallefacsobranteOfFacturassobranteCollectionFacturassobrante.getFacturassobranteCollection().remove(facturassobranteCollectionFacturassobrante);
                    oldIddetallefacsobranteOfFacturassobranteCollectionFacturassobrante = em.merge(oldIddetallefacsobranteOfFacturassobranteCollectionFacturassobrante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefacturasobrante detallefacturasobrante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefacturasobrante persistentDetallefacturasobrante = em.find(Detallefacturasobrante.class, detallefacturasobrante.getIddetallefacsobrante());
            Aguasobrante idaguasobranteOld = persistentDetallefacturasobrante.getIdaguasobrante();
            Aguasobrante idaguasobranteNew = detallefacturasobrante.getIdaguasobrante();
            Tarifassobrante idtarifassobranteOld = persistentDetallefacturasobrante.getIdtarifassobrante();
            Tarifassobrante idtarifassobranteNew = detallefacturasobrante.getIdtarifassobrante();
            Collection<Facturassobrante> facturassobranteCollectionOld = persistentDetallefacturasobrante.getFacturassobranteCollection();
            Collection<Facturassobrante> facturassobranteCollectionNew = detallefacturasobrante.getFacturassobranteCollection();
            List<String> illegalOrphanMessages = null;
            for (Facturassobrante facturassobranteCollectionOldFacturassobrante : facturassobranteCollectionOld) {
                if (!facturassobranteCollectionNew.contains(facturassobranteCollectionOldFacturassobrante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturassobrante " + facturassobranteCollectionOldFacturassobrante + " since its iddetallefacsobrante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idaguasobranteNew != null) {
                idaguasobranteNew = em.getReference(idaguasobranteNew.getClass(), idaguasobranteNew.getIdaguasobrante());
                detallefacturasobrante.setIdaguasobrante(idaguasobranteNew);
            }
            if (idtarifassobranteNew != null) {
                idtarifassobranteNew = em.getReference(idtarifassobranteNew.getClass(), idtarifassobranteNew.getIdtarifassobrante());
                detallefacturasobrante.setIdtarifassobrante(idtarifassobranteNew);
            }
            Collection<Facturassobrante> attachedFacturassobranteCollectionNew = new ArrayList<Facturassobrante>();
            for (Facturassobrante facturassobranteCollectionNewFacturassobranteToAttach : facturassobranteCollectionNew) {
                facturassobranteCollectionNewFacturassobranteToAttach = em.getReference(facturassobranteCollectionNewFacturassobranteToAttach.getClass(), facturassobranteCollectionNewFacturassobranteToAttach.getIdfacturasobrante());
                attachedFacturassobranteCollectionNew.add(facturassobranteCollectionNewFacturassobranteToAttach);
            }
            facturassobranteCollectionNew = attachedFacturassobranteCollectionNew;
            detallefacturasobrante.setFacturassobranteCollection(facturassobranteCollectionNew);
            detallefacturasobrante = em.merge(detallefacturasobrante);
            if (idaguasobranteOld != null && !idaguasobranteOld.equals(idaguasobranteNew)) {
                idaguasobranteOld.getDetallefacturasobranteCollection().remove(detallefacturasobrante);
                idaguasobranteOld = em.merge(idaguasobranteOld);
            }
            if (idaguasobranteNew != null && !idaguasobranteNew.equals(idaguasobranteOld)) {
                idaguasobranteNew.getDetallefacturasobranteCollection().add(detallefacturasobrante);
                idaguasobranteNew = em.merge(idaguasobranteNew);
            }
            if (idtarifassobranteOld != null && !idtarifassobranteOld.equals(idtarifassobranteNew)) {
                idtarifassobranteOld.getDetallefacturasobranteCollection().remove(detallefacturasobrante);
                idtarifassobranteOld = em.merge(idtarifassobranteOld);
            }
            if (idtarifassobranteNew != null && !idtarifassobranteNew.equals(idtarifassobranteOld)) {
                idtarifassobranteNew.getDetallefacturasobranteCollection().add(detallefacturasobrante);
                idtarifassobranteNew = em.merge(idtarifassobranteNew);
            }
            for (Facturassobrante facturassobranteCollectionNewFacturassobrante : facturassobranteCollectionNew) {
                if (!facturassobranteCollectionOld.contains(facturassobranteCollectionNewFacturassobrante)) {
                    Detallefacturasobrante oldIddetallefacsobranteOfFacturassobranteCollectionNewFacturassobrante = facturassobranteCollectionNewFacturassobrante.getIddetallefacsobrante();
                    facturassobranteCollectionNewFacturassobrante.setIddetallefacsobrante(detallefacturasobrante);
                    facturassobranteCollectionNewFacturassobrante = em.merge(facturassobranteCollectionNewFacturassobrante);
                    if (oldIddetallefacsobranteOfFacturassobranteCollectionNewFacturassobrante != null && !oldIddetallefacsobranteOfFacturassobranteCollectionNewFacturassobrante.equals(detallefacturasobrante)) {
                        oldIddetallefacsobranteOfFacturassobranteCollectionNewFacturassobrante.getFacturassobranteCollection().remove(facturassobranteCollectionNewFacturassobrante);
                        oldIddetallefacsobranteOfFacturassobranteCollectionNewFacturassobrante = em.merge(oldIddetallefacsobranteOfFacturassobranteCollectionNewFacturassobrante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallefacturasobrante.getIddetallefacsobrante();
                if (findDetallefacturasobrante(id) == null) {
                    throw new NonexistentEntityException("The detallefacturasobrante with id " + id + " no longer exists.");
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
            Detallefacturasobrante detallefacturasobrante;
            try {
                detallefacturasobrante = em.getReference(Detallefacturasobrante.class, id);
                detallefacturasobrante.getIddetallefacsobrante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefacturasobrante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Facturassobrante> facturassobranteCollectionOrphanCheck = detallefacturasobrante.getFacturassobranteCollection();
            for (Facturassobrante facturassobranteCollectionOrphanCheckFacturassobrante : facturassobranteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detallefacturasobrante (" + detallefacturasobrante + ") cannot be destroyed since the Facturassobrante " + facturassobranteCollectionOrphanCheckFacturassobrante + " in its facturassobranteCollection field has a non-nullable iddetallefacsobrante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Aguasobrante idaguasobrante = detallefacturasobrante.getIdaguasobrante();
            if (idaguasobrante != null) {
                idaguasobrante.getDetallefacturasobranteCollection().remove(detallefacturasobrante);
                idaguasobrante = em.merge(idaguasobrante);
            }
            Tarifassobrante idtarifassobrante = detallefacturasobrante.getIdtarifassobrante();
            if (idtarifassobrante != null) {
                idtarifassobrante.getDetallefacturasobranteCollection().remove(detallefacturasobrante);
                idtarifassobrante = em.merge(idtarifassobrante);
            }
            em.remove(detallefacturasobrante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefacturasobrante> findDetallefacturasobranteEntities() {
        return findDetallefacturasobranteEntities(true, -1, -1);
    }

    public List<Detallefacturasobrante> findDetallefacturasobranteEntities(int maxResults, int firstResult) {
        return findDetallefacturasobranteEntities(false, maxResults, firstResult);
    }

    private List<Detallefacturasobrante> findDetallefacturasobranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefacturasobrante.class));
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

    public Detallefacturasobrante findDetallefacturasobrante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefacturasobrante.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturasobranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefacturasobrante> rt = cq.from(Detallefacturasobrante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
