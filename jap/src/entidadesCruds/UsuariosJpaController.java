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
import entidades.Institucion;
import entidades.Medidor;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Aguasobrante;
import entidades.Aguaganaderia;
import entidades.Usuarios;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tech-Usuario
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        if (usuarios.getMedidorCollection() == null) {
            usuarios.setMedidorCollection(new ArrayList<Medidor>());
        }
        if (usuarios.getAguasobranteCollection() == null) {
            usuarios.setAguasobranteCollection(new ArrayList<Aguasobrante>());
        }
        if (usuarios.getMedidorCollection1() == null) {
            usuarios.setMedidorCollection1(new ArrayList<Medidor>());
        }
        if (usuarios.getAguaganaderiaCollection() == null) {
            usuarios.setAguaganaderiaCollection(new ArrayList<Aguaganaderia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Institucion idinstitucion = usuarios.getIdinstitucion();
            if (idinstitucion != null) {
                idinstitucion = em.getReference(idinstitucion.getClass(), idinstitucion.getIdinstitucion());
                usuarios.setIdinstitucion(idinstitucion);
            }
            Collection<Medidor> attachedMedidorCollection = new ArrayList<Medidor>();
            for (Medidor medidorCollectionMedidorToAttach : usuarios.getMedidorCollection()) {
                medidorCollectionMedidorToAttach = em.getReference(medidorCollectionMedidorToAttach.getClass(), medidorCollectionMedidorToAttach.getIdmedidor());
                attachedMedidorCollection.add(medidorCollectionMedidorToAttach);
            }
            usuarios.setMedidorCollection(attachedMedidorCollection);
            Collection<Aguasobrante> attachedAguasobranteCollection = new ArrayList<Aguasobrante>();
            for (Aguasobrante aguasobranteCollectionAguasobranteToAttach : usuarios.getAguasobranteCollection()) {
                aguasobranteCollectionAguasobranteToAttach = em.getReference(aguasobranteCollectionAguasobranteToAttach.getClass(), aguasobranteCollectionAguasobranteToAttach.getIdaguasobrante());
                attachedAguasobranteCollection.add(aguasobranteCollectionAguasobranteToAttach);
            }
            usuarios.setAguasobranteCollection(attachedAguasobranteCollection);
            Collection<Medidor> attachedMedidorCollection1 = new ArrayList<Medidor>();
            for (Medidor medidorCollection1MedidorToAttach : usuarios.getMedidorCollection1()) {
                medidorCollection1MedidorToAttach = em.getReference(medidorCollection1MedidorToAttach.getClass(), medidorCollection1MedidorToAttach.getIdmedidor());
                attachedMedidorCollection1.add(medidorCollection1MedidorToAttach);
            }
            usuarios.setMedidorCollection1(attachedMedidorCollection1);
            Collection<Aguaganaderia> attachedAguaganaderiaCollection = new ArrayList<Aguaganaderia>();
            for (Aguaganaderia aguaganaderiaCollectionAguaganaderiaToAttach : usuarios.getAguaganaderiaCollection()) {
                aguaganaderiaCollectionAguaganaderiaToAttach = em.getReference(aguaganaderiaCollectionAguaganaderiaToAttach.getClass(), aguaganaderiaCollectionAguaganaderiaToAttach.getIdaguaganaderia());
                attachedAguaganaderiaCollection.add(aguaganaderiaCollectionAguaganaderiaToAttach);
            }
            usuarios.setAguaganaderiaCollection(attachedAguaganaderiaCollection);
            em.persist(usuarios);
            if (idinstitucion != null) {
                idinstitucion.getUsuariosCollection().add(usuarios);
                idinstitucion = em.merge(idinstitucion);
            }
            for (Medidor medidorCollectionMedidor : usuarios.getMedidorCollection()) {
                medidorCollectionMedidor.getUsuariosCollection().add(usuarios);
                medidorCollectionMedidor = em.merge(medidorCollectionMedidor);
            }
            for (Aguasobrante aguasobranteCollectionAguasobrante : usuarios.getAguasobranteCollection()) {
                Usuarios oldIdusuarioOfAguasobranteCollectionAguasobrante = aguasobranteCollectionAguasobrante.getIdusuario();
                aguasobranteCollectionAguasobrante.setIdusuario(usuarios);
                aguasobranteCollectionAguasobrante = em.merge(aguasobranteCollectionAguasobrante);
                if (oldIdusuarioOfAguasobranteCollectionAguasobrante != null) {
                    oldIdusuarioOfAguasobranteCollectionAguasobrante.getAguasobranteCollection().remove(aguasobranteCollectionAguasobrante);
                    oldIdusuarioOfAguasobranteCollectionAguasobrante = em.merge(oldIdusuarioOfAguasobranteCollectionAguasobrante);
                }
            }
            for (Medidor medidorCollection1Medidor : usuarios.getMedidorCollection1()) {
                Usuarios oldIdusuarioOfMedidorCollection1Medidor = medidorCollection1Medidor.getIdusuario();
                medidorCollection1Medidor.setIdusuario(usuarios);
                medidorCollection1Medidor = em.merge(medidorCollection1Medidor);
                if (oldIdusuarioOfMedidorCollection1Medidor != null) {
                    oldIdusuarioOfMedidorCollection1Medidor.getMedidorCollection1().remove(medidorCollection1Medidor);
                    oldIdusuarioOfMedidorCollection1Medidor = em.merge(oldIdusuarioOfMedidorCollection1Medidor);
                }
            }
            for (Aguaganaderia aguaganaderiaCollectionAguaganaderia : usuarios.getAguaganaderiaCollection()) {
                Usuarios oldIdusuarioOfAguaganaderiaCollectionAguaganaderia = aguaganaderiaCollectionAguaganaderia.getIdusuario();
                aguaganaderiaCollectionAguaganaderia.setIdusuario(usuarios);
                aguaganaderiaCollectionAguaganaderia = em.merge(aguaganaderiaCollectionAguaganaderia);
                if (oldIdusuarioOfAguaganaderiaCollectionAguaganaderia != null) {
                    oldIdusuarioOfAguaganaderiaCollectionAguaganaderia.getAguaganaderiaCollection().remove(aguaganaderiaCollectionAguaganaderia);
                    oldIdusuarioOfAguaganaderiaCollectionAguaganaderia = em.merge(oldIdusuarioOfAguaganaderiaCollectionAguaganaderia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdusuario());
            Institucion idinstitucionOld = persistentUsuarios.getIdinstitucion();
            Institucion idinstitucionNew = usuarios.getIdinstitucion();
            Collection<Medidor> medidorCollectionOld = persistentUsuarios.getMedidorCollection();
            Collection<Medidor> medidorCollectionNew = usuarios.getMedidorCollection();
            Collection<Aguasobrante> aguasobranteCollectionOld = persistentUsuarios.getAguasobranteCollection();
            Collection<Aguasobrante> aguasobranteCollectionNew = usuarios.getAguasobranteCollection();
            Collection<Medidor> medidorCollection1Old = persistentUsuarios.getMedidorCollection1();
            Collection<Medidor> medidorCollection1New = usuarios.getMedidorCollection1();
            Collection<Aguaganaderia> aguaganaderiaCollectionOld = persistentUsuarios.getAguaganaderiaCollection();
            Collection<Aguaganaderia> aguaganaderiaCollectionNew = usuarios.getAguaganaderiaCollection();
            List<String> illegalOrphanMessages = null;
            for (Medidor medidorCollection1OldMedidor : medidorCollection1Old) {
                if (!medidorCollection1New.contains(medidorCollection1OldMedidor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Medidor " + medidorCollection1OldMedidor + " since its idusuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idinstitucionNew != null) {
                idinstitucionNew = em.getReference(idinstitucionNew.getClass(), idinstitucionNew.getIdinstitucion());
                usuarios.setIdinstitucion(idinstitucionNew);
            }
            Collection<Medidor> attachedMedidorCollectionNew = new ArrayList<Medidor>();
            for (Medidor medidorCollectionNewMedidorToAttach : medidorCollectionNew) {
                medidorCollectionNewMedidorToAttach = em.getReference(medidorCollectionNewMedidorToAttach.getClass(), medidorCollectionNewMedidorToAttach.getIdmedidor());
                attachedMedidorCollectionNew.add(medidorCollectionNewMedidorToAttach);
            }
            medidorCollectionNew = attachedMedidorCollectionNew;
            usuarios.setMedidorCollection(medidorCollectionNew);
            Collection<Aguasobrante> attachedAguasobranteCollectionNew = new ArrayList<Aguasobrante>();
            for (Aguasobrante aguasobranteCollectionNewAguasobranteToAttach : aguasobranteCollectionNew) {
                aguasobranteCollectionNewAguasobranteToAttach = em.getReference(aguasobranteCollectionNewAguasobranteToAttach.getClass(), aguasobranteCollectionNewAguasobranteToAttach.getIdaguasobrante());
                attachedAguasobranteCollectionNew.add(aguasobranteCollectionNewAguasobranteToAttach);
            }
            aguasobranteCollectionNew = attachedAguasobranteCollectionNew;
            usuarios.setAguasobranteCollection(aguasobranteCollectionNew);
            Collection<Medidor> attachedMedidorCollection1New = new ArrayList<Medidor>();
            for (Medidor medidorCollection1NewMedidorToAttach : medidorCollection1New) {
                medidorCollection1NewMedidorToAttach = em.getReference(medidorCollection1NewMedidorToAttach.getClass(), medidorCollection1NewMedidorToAttach.getIdmedidor());
                attachedMedidorCollection1New.add(medidorCollection1NewMedidorToAttach);
            }
            medidorCollection1New = attachedMedidorCollection1New;
            usuarios.setMedidorCollection1(medidorCollection1New);
            Collection<Aguaganaderia> attachedAguaganaderiaCollectionNew = new ArrayList<Aguaganaderia>();
            for (Aguaganaderia aguaganaderiaCollectionNewAguaganaderiaToAttach : aguaganaderiaCollectionNew) {
                aguaganaderiaCollectionNewAguaganaderiaToAttach = em.getReference(aguaganaderiaCollectionNewAguaganaderiaToAttach.getClass(), aguaganaderiaCollectionNewAguaganaderiaToAttach.getIdaguaganaderia());
                attachedAguaganaderiaCollectionNew.add(aguaganaderiaCollectionNewAguaganaderiaToAttach);
            }
            aguaganaderiaCollectionNew = attachedAguaganaderiaCollectionNew;
            usuarios.setAguaganaderiaCollection(aguaganaderiaCollectionNew);
            usuarios = em.merge(usuarios);
            if (idinstitucionOld != null && !idinstitucionOld.equals(idinstitucionNew)) {
                idinstitucionOld.getUsuariosCollection().remove(usuarios);
                idinstitucionOld = em.merge(idinstitucionOld);
            }
            if (idinstitucionNew != null && !idinstitucionNew.equals(idinstitucionOld)) {
                idinstitucionNew.getUsuariosCollection().add(usuarios);
                idinstitucionNew = em.merge(idinstitucionNew);
            }
            for (Medidor medidorCollectionOldMedidor : medidorCollectionOld) {
                if (!medidorCollectionNew.contains(medidorCollectionOldMedidor)) {
                    medidorCollectionOldMedidor.getUsuariosCollection().remove(usuarios);
                    medidorCollectionOldMedidor = em.merge(medidorCollectionOldMedidor);
                }
            }
            for (Medidor medidorCollectionNewMedidor : medidorCollectionNew) {
                if (!medidorCollectionOld.contains(medidorCollectionNewMedidor)) {
                    medidorCollectionNewMedidor.getUsuariosCollection().add(usuarios);
                    medidorCollectionNewMedidor = em.merge(medidorCollectionNewMedidor);
                }
            }
            for (Aguasobrante aguasobranteCollectionOldAguasobrante : aguasobranteCollectionOld) {
                if (!aguasobranteCollectionNew.contains(aguasobranteCollectionOldAguasobrante)) {
                    aguasobranteCollectionOldAguasobrante.setIdusuario(null);
                    aguasobranteCollectionOldAguasobrante = em.merge(aguasobranteCollectionOldAguasobrante);
                }
            }
            for (Aguasobrante aguasobranteCollectionNewAguasobrante : aguasobranteCollectionNew) {
                if (!aguasobranteCollectionOld.contains(aguasobranteCollectionNewAguasobrante)) {
                    Usuarios oldIdusuarioOfAguasobranteCollectionNewAguasobrante = aguasobranteCollectionNewAguasobrante.getIdusuario();
                    aguasobranteCollectionNewAguasobrante.setIdusuario(usuarios);
                    aguasobranteCollectionNewAguasobrante = em.merge(aguasobranteCollectionNewAguasobrante);
                    if (oldIdusuarioOfAguasobranteCollectionNewAguasobrante != null && !oldIdusuarioOfAguasobranteCollectionNewAguasobrante.equals(usuarios)) {
                        oldIdusuarioOfAguasobranteCollectionNewAguasobrante.getAguasobranteCollection().remove(aguasobranteCollectionNewAguasobrante);
                        oldIdusuarioOfAguasobranteCollectionNewAguasobrante = em.merge(oldIdusuarioOfAguasobranteCollectionNewAguasobrante);
                    }
                }
            }
            for (Medidor medidorCollection1NewMedidor : medidorCollection1New) {
                if (!medidorCollection1Old.contains(medidorCollection1NewMedidor)) {
                    Usuarios oldIdusuarioOfMedidorCollection1NewMedidor = medidorCollection1NewMedidor.getIdusuario();
                    medidorCollection1NewMedidor.setIdusuario(usuarios);
                    medidorCollection1NewMedidor = em.merge(medidorCollection1NewMedidor);
                    if (oldIdusuarioOfMedidorCollection1NewMedidor != null && !oldIdusuarioOfMedidorCollection1NewMedidor.equals(usuarios)) {
                        oldIdusuarioOfMedidorCollection1NewMedidor.getMedidorCollection1().remove(medidorCollection1NewMedidor);
                        oldIdusuarioOfMedidorCollection1NewMedidor = em.merge(oldIdusuarioOfMedidorCollection1NewMedidor);
                    }
                }
            }
            for (Aguaganaderia aguaganaderiaCollectionOldAguaganaderia : aguaganaderiaCollectionOld) {
                if (!aguaganaderiaCollectionNew.contains(aguaganaderiaCollectionOldAguaganaderia)) {
                    aguaganaderiaCollectionOldAguaganaderia.setIdusuario(null);
                    aguaganaderiaCollectionOldAguaganaderia = em.merge(aguaganaderiaCollectionOldAguaganaderia);
                }
            }
            for (Aguaganaderia aguaganaderiaCollectionNewAguaganaderia : aguaganaderiaCollectionNew) {
                if (!aguaganaderiaCollectionOld.contains(aguaganaderiaCollectionNewAguaganaderia)) {
                    Usuarios oldIdusuarioOfAguaganaderiaCollectionNewAguaganaderia = aguaganaderiaCollectionNewAguaganaderia.getIdusuario();
                    aguaganaderiaCollectionNewAguaganaderia.setIdusuario(usuarios);
                    aguaganaderiaCollectionNewAguaganaderia = em.merge(aguaganaderiaCollectionNewAguaganaderia);
                    if (oldIdusuarioOfAguaganaderiaCollectionNewAguaganaderia != null && !oldIdusuarioOfAguaganaderiaCollectionNewAguaganaderia.equals(usuarios)) {
                        oldIdusuarioOfAguaganaderiaCollectionNewAguaganaderia.getAguaganaderiaCollection().remove(aguaganaderiaCollectionNewAguaganaderia);
                        oldIdusuarioOfAguaganaderiaCollectionNewAguaganaderia = em.merge(oldIdusuarioOfAguaganaderiaCollectionNewAguaganaderia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdusuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Medidor> medidorCollection1OrphanCheck = usuarios.getMedidorCollection1();
            for (Medidor medidorCollection1OrphanCheckMedidor : medidorCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Medidor " + medidorCollection1OrphanCheckMedidor + " in its medidorCollection1 field has a non-nullable idusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Institucion idinstitucion = usuarios.getIdinstitucion();
            if (idinstitucion != null) {
                idinstitucion.getUsuariosCollection().remove(usuarios);
                idinstitucion = em.merge(idinstitucion);
            }
            Collection<Medidor> medidorCollection = usuarios.getMedidorCollection();
            for (Medidor medidorCollectionMedidor : medidorCollection) {
                medidorCollectionMedidor.getUsuariosCollection().remove(usuarios);
                medidorCollectionMedidor = em.merge(medidorCollectionMedidor);
            }
            Collection<Aguasobrante> aguasobranteCollection = usuarios.getAguasobranteCollection();
            for (Aguasobrante aguasobranteCollectionAguasobrante : aguasobranteCollection) {
                aguasobranteCollectionAguasobrante.setIdusuario(null);
                aguasobranteCollectionAguasobrante = em.merge(aguasobranteCollectionAguasobrante);
            }
            Collection<Aguaganaderia> aguaganaderiaCollection = usuarios.getAguaganaderiaCollection();
            for (Aguaganaderia aguaganaderiaCollectionAguaganaderia : aguaganaderiaCollection) {
                aguaganaderiaCollectionAguaganaderia.setIdusuario(null);
                aguaganaderiaCollectionAguaganaderia = em.merge(aguaganaderiaCollectionAguaganaderia);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
