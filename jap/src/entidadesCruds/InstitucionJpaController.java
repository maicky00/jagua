/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesCruds;

import entidades.Institucion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Usuarios;
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
public class InstitucionJpaController implements Serializable {

    public InstitucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Institucion institucion) {
        if (institucion.getUsuariosCollection() == null) {
            institucion.setUsuariosCollection(new ArrayList<Usuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Usuarios> attachedUsuariosCollection = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionUsuariosToAttach : institucion.getUsuariosCollection()) {
                usuariosCollectionUsuariosToAttach = em.getReference(usuariosCollectionUsuariosToAttach.getClass(), usuariosCollectionUsuariosToAttach.getIdusuario());
                attachedUsuariosCollection.add(usuariosCollectionUsuariosToAttach);
            }
            institucion.setUsuariosCollection(attachedUsuariosCollection);
            em.persist(institucion);
            for (Usuarios usuariosCollectionUsuarios : institucion.getUsuariosCollection()) {
                Institucion oldIdinstitucionOfUsuariosCollectionUsuarios = usuariosCollectionUsuarios.getIdinstitucion();
                usuariosCollectionUsuarios.setIdinstitucion(institucion);
                usuariosCollectionUsuarios = em.merge(usuariosCollectionUsuarios);
                if (oldIdinstitucionOfUsuariosCollectionUsuarios != null) {
                    oldIdinstitucionOfUsuariosCollectionUsuarios.getUsuariosCollection().remove(usuariosCollectionUsuarios);
                    oldIdinstitucionOfUsuariosCollectionUsuarios = em.merge(oldIdinstitucionOfUsuariosCollectionUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Institucion institucion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Institucion persistentInstitucion = em.find(Institucion.class, institucion.getIdinstitucion());
            Collection<Usuarios> usuariosCollectionOld = persistentInstitucion.getUsuariosCollection();
            Collection<Usuarios> usuariosCollectionNew = institucion.getUsuariosCollection();
            List<String> illegalOrphanMessages = null;
            for (Usuarios usuariosCollectionOldUsuarios : usuariosCollectionOld) {
                if (!usuariosCollectionNew.contains(usuariosCollectionOldUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarios " + usuariosCollectionOldUsuarios + " since its idinstitucion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Usuarios> attachedUsuariosCollectionNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionNewUsuariosToAttach : usuariosCollectionNew) {
                usuariosCollectionNewUsuariosToAttach = em.getReference(usuariosCollectionNewUsuariosToAttach.getClass(), usuariosCollectionNewUsuariosToAttach.getIdusuario());
                attachedUsuariosCollectionNew.add(usuariosCollectionNewUsuariosToAttach);
            }
            usuariosCollectionNew = attachedUsuariosCollectionNew;
            institucion.setUsuariosCollection(usuariosCollectionNew);
            institucion = em.merge(institucion);
            for (Usuarios usuariosCollectionNewUsuarios : usuariosCollectionNew) {
                if (!usuariosCollectionOld.contains(usuariosCollectionNewUsuarios)) {
                    Institucion oldIdinstitucionOfUsuariosCollectionNewUsuarios = usuariosCollectionNewUsuarios.getIdinstitucion();
                    usuariosCollectionNewUsuarios.setIdinstitucion(institucion);
                    usuariosCollectionNewUsuarios = em.merge(usuariosCollectionNewUsuarios);
                    if (oldIdinstitucionOfUsuariosCollectionNewUsuarios != null && !oldIdinstitucionOfUsuariosCollectionNewUsuarios.equals(institucion)) {
                        oldIdinstitucionOfUsuariosCollectionNewUsuarios.getUsuariosCollection().remove(usuariosCollectionNewUsuarios);
                        oldIdinstitucionOfUsuariosCollectionNewUsuarios = em.merge(oldIdinstitucionOfUsuariosCollectionNewUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = institucion.getIdinstitucion();
                if (findInstitucion(id) == null) {
                    throw new NonexistentEntityException("The institucion with id " + id + " no longer exists.");
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
            Institucion institucion;
            try {
                institucion = em.getReference(Institucion.class, id);
                institucion.getIdinstitucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The institucion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Usuarios> usuariosCollectionOrphanCheck = institucion.getUsuariosCollection();
            for (Usuarios usuariosCollectionOrphanCheckUsuarios : usuariosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Institucion (" + institucion + ") cannot be destroyed since the Usuarios " + usuariosCollectionOrphanCheckUsuarios + " in its usuariosCollection field has a non-nullable idinstitucion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(institucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Institucion> findInstitucionEntities() {
        return findInstitucionEntities(true, -1, -1);
    }

    public List<Institucion> findInstitucionEntities(int maxResults, int firstResult) {
        return findInstitucionEntities(false, maxResults, firstResult);
    }

    private List<Institucion> findInstitucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Institucion.class));
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

    public Institucion findInstitucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Institucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstitucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Institucion> rt = cq.from(Institucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
