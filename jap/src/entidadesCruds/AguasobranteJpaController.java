/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Aguasobrante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Usuarios;
import entidades.Detallefacturasobrante;
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
public class AguasobranteJpaController implements Serializable {

    public AguasobranteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aguasobrante aguasobrante) {
        if (aguasobrante.getDetallefacturasobranteCollection() == null) {
            aguasobrante.setDetallefacturasobranteCollection(new ArrayList<Detallefacturasobrante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios idusuario = aguasobrante.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                aguasobrante.setIdusuario(idusuario);
            }
            Collection<Detallefacturasobrante> attachedDetallefacturasobranteCollection = new ArrayList<Detallefacturasobrante>();
            for (Detallefacturasobrante detallefacturasobranteCollectionDetallefacturasobranteToAttach : aguasobrante.getDetallefacturasobranteCollection()) {
                detallefacturasobranteCollectionDetallefacturasobranteToAttach = em.getReference(detallefacturasobranteCollectionDetallefacturasobranteToAttach.getClass(), detallefacturasobranteCollectionDetallefacturasobranteToAttach.getIddetallefacsobrante());
                attachedDetallefacturasobranteCollection.add(detallefacturasobranteCollectionDetallefacturasobranteToAttach);
            }
            aguasobrante.setDetallefacturasobranteCollection(attachedDetallefacturasobranteCollection);
            em.persist(aguasobrante);
            if (idusuario != null) {
                idusuario.getAguasobranteCollection().add(aguasobrante);
                idusuario = em.merge(idusuario);
            }
            for (Detallefacturasobrante detallefacturasobranteCollectionDetallefacturasobrante : aguasobrante.getDetallefacturasobranteCollection()) {
                Aguasobrante oldIdaguasobranteOfDetallefacturasobranteCollectionDetallefacturasobrante = detallefacturasobranteCollectionDetallefacturasobrante.getIdaguasobrante();
                detallefacturasobranteCollectionDetallefacturasobrante.setIdaguasobrante(aguasobrante);
                detallefacturasobranteCollectionDetallefacturasobrante = em.merge(detallefacturasobranteCollectionDetallefacturasobrante);
                if (oldIdaguasobranteOfDetallefacturasobranteCollectionDetallefacturasobrante != null) {
                    oldIdaguasobranteOfDetallefacturasobranteCollectionDetallefacturasobrante.getDetallefacturasobranteCollection().remove(detallefacturasobranteCollectionDetallefacturasobrante);
                    oldIdaguasobranteOfDetallefacturasobranteCollectionDetallefacturasobrante = em.merge(oldIdaguasobranteOfDetallefacturasobranteCollectionDetallefacturasobrante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aguasobrante aguasobrante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aguasobrante persistentAguasobrante = em.find(Aguasobrante.class, aguasobrante.getIdaguasobrante());
            Usuarios idusuarioOld = persistentAguasobrante.getIdusuario();
            Usuarios idusuarioNew = aguasobrante.getIdusuario();
            Collection<Detallefacturasobrante> detallefacturasobranteCollectionOld = persistentAguasobrante.getDetallefacturasobranteCollection();
            Collection<Detallefacturasobrante> detallefacturasobranteCollectionNew = aguasobrante.getDetallefacturasobranteCollection();
            List<String> illegalOrphanMessages = null;
            for (Detallefacturasobrante detallefacturasobranteCollectionOldDetallefacturasobrante : detallefacturasobranteCollectionOld) {
                if (!detallefacturasobranteCollectionNew.contains(detallefacturasobranteCollectionOldDetallefacturasobrante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefacturasobrante " + detallefacturasobranteCollectionOldDetallefacturasobrante + " since its idaguasobrante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                aguasobrante.setIdusuario(idusuarioNew);
            }
            Collection<Detallefacturasobrante> attachedDetallefacturasobranteCollectionNew = new ArrayList<Detallefacturasobrante>();
            for (Detallefacturasobrante detallefacturasobranteCollectionNewDetallefacturasobranteToAttach : detallefacturasobranteCollectionNew) {
                detallefacturasobranteCollectionNewDetallefacturasobranteToAttach = em.getReference(detallefacturasobranteCollectionNewDetallefacturasobranteToAttach.getClass(), detallefacturasobranteCollectionNewDetallefacturasobranteToAttach.getIddetallefacsobrante());
                attachedDetallefacturasobranteCollectionNew.add(detallefacturasobranteCollectionNewDetallefacturasobranteToAttach);
            }
            detallefacturasobranteCollectionNew = attachedDetallefacturasobranteCollectionNew;
            aguasobrante.setDetallefacturasobranteCollection(detallefacturasobranteCollectionNew);
            aguasobrante = em.merge(aguasobrante);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getAguasobranteCollection().remove(aguasobrante);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getAguasobranteCollection().add(aguasobrante);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Detallefacturasobrante detallefacturasobranteCollectionNewDetallefacturasobrante : detallefacturasobranteCollectionNew) {
                if (!detallefacturasobranteCollectionOld.contains(detallefacturasobranteCollectionNewDetallefacturasobrante)) {
                    Aguasobrante oldIdaguasobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante = detallefacturasobranteCollectionNewDetallefacturasobrante.getIdaguasobrante();
                    detallefacturasobranteCollectionNewDetallefacturasobrante.setIdaguasobrante(aguasobrante);
                    detallefacturasobranteCollectionNewDetallefacturasobrante = em.merge(detallefacturasobranteCollectionNewDetallefacturasobrante);
                    if (oldIdaguasobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante != null && !oldIdaguasobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante.equals(aguasobrante)) {
                        oldIdaguasobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante.getDetallefacturasobranteCollection().remove(detallefacturasobranteCollectionNewDetallefacturasobrante);
                        oldIdaguasobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante = em.merge(oldIdaguasobranteOfDetallefacturasobranteCollectionNewDetallefacturasobrante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aguasobrante.getIdaguasobrante();
                if (findAguasobrante(id) == null) {
                    throw new NonexistentEntityException("The aguasobrante with id " + id + " no longer exists.");
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
            Aguasobrante aguasobrante;
            try {
                aguasobrante = em.getReference(Aguasobrante.class, id);
                aguasobrante.getIdaguasobrante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aguasobrante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detallefacturasobrante> detallefacturasobranteCollectionOrphanCheck = aguasobrante.getDetallefacturasobranteCollection();
            for (Detallefacturasobrante detallefacturasobranteCollectionOrphanCheckDetallefacturasobrante : detallefacturasobranteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Aguasobrante (" + aguasobrante + ") cannot be destroyed since the Detallefacturasobrante " + detallefacturasobranteCollectionOrphanCheckDetallefacturasobrante + " in its detallefacturasobranteCollection field has a non-nullable idaguasobrante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuarios idusuario = aguasobrante.getIdusuario();
            if (idusuario != null) {
                idusuario.getAguasobranteCollection().remove(aguasobrante);
                idusuario = em.merge(idusuario);
            }
            em.remove(aguasobrante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aguasobrante> findAguasobranteEntities() {
        return findAguasobranteEntities(true, -1, -1);
    }

    public List<Aguasobrante> findAguasobranteEntities(int maxResults, int firstResult) {
        return findAguasobranteEntities(false, maxResults, firstResult);
    }

    private List<Aguasobrante> findAguasobranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aguasobrante.class));
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

    public Aguasobrante findAguasobrante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aguasobrante.class, id);
        } finally {
            em.close();
        }
    }

    public int getAguasobranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aguasobrante> rt = cq.from(Aguasobrante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
