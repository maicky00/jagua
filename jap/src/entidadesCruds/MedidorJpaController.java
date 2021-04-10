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
import entidades.Usuarios;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Asistenciapesillo;
import entidades.Corte;
import entidades.Asistencia;
import entidades.Pagosnuevomed;
import entidades.Detallefactura;
import entidades.Medidor;
import entidadesCruds.exceptions.IllegalOrphanException;
import entidadesCruds.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tech-Usuario
 */
public class MedidorJpaController implements Serializable {

    public MedidorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medidor medidor) {
        if (medidor.getUsuariosCollection() == null) {
            medidor.setUsuariosCollection(new ArrayList<Usuarios>());
        }
        if (medidor.getAsistenciapesilloCollection() == null) {
            medidor.setAsistenciapesilloCollection(new ArrayList<Asistenciapesillo>());
        }
        if (medidor.getCorteCollection() == null) {
            medidor.setCorteCollection(new ArrayList<Corte>());
        }
        if (medidor.getAsistenciaCollection() == null) {
            medidor.setAsistenciaCollection(new ArrayList<Asistencia>());
        }
        if (medidor.getPagosnuevomedCollection() == null) {
            medidor.setPagosnuevomedCollection(new ArrayList<Pagosnuevomed>());
        }
        if (medidor.getDetallefacturaCollection() == null) {
            medidor.setDetallefacturaCollection(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios idusuario = medidor.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                medidor.setIdusuario(idusuario);
            }
            Collection<Usuarios> attachedUsuariosCollection = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionUsuariosToAttach : medidor.getUsuariosCollection()) {
                usuariosCollectionUsuariosToAttach = em.getReference(usuariosCollectionUsuariosToAttach.getClass(), usuariosCollectionUsuariosToAttach.getIdusuario());
                attachedUsuariosCollection.add(usuariosCollectionUsuariosToAttach);
            }
            medidor.setUsuariosCollection(attachedUsuariosCollection);
            Collection<Asistenciapesillo> attachedAsistenciapesilloCollection = new ArrayList<Asistenciapesillo>();
            for (Asistenciapesillo asistenciapesilloCollectionAsistenciapesilloToAttach : medidor.getAsistenciapesilloCollection()) {
                asistenciapesilloCollectionAsistenciapesilloToAttach = em.getReference(asistenciapesilloCollectionAsistenciapesilloToAttach.getClass(), asistenciapesilloCollectionAsistenciapesilloToAttach.getIdasistenciapesillo());
                attachedAsistenciapesilloCollection.add(asistenciapesilloCollectionAsistenciapesilloToAttach);
            }
            medidor.setAsistenciapesilloCollection(attachedAsistenciapesilloCollection);
            Collection<Corte> attachedCorteCollection = new ArrayList<Corte>();
            for (Corte corteCollectionCorteToAttach : medidor.getCorteCollection()) {
                corteCollectionCorteToAttach = em.getReference(corteCollectionCorteToAttach.getClass(), corteCollectionCorteToAttach.getIdcorte());
                attachedCorteCollection.add(corteCollectionCorteToAttach);
            }
            medidor.setCorteCollection(attachedCorteCollection);
            Collection<Asistencia> attachedAsistenciaCollection = new ArrayList<Asistencia>();
            for (Asistencia asistenciaCollectionAsistenciaToAttach : medidor.getAsistenciaCollection()) {
                asistenciaCollectionAsistenciaToAttach = em.getReference(asistenciaCollectionAsistenciaToAttach.getClass(), asistenciaCollectionAsistenciaToAttach.getIdasistencia());
                attachedAsistenciaCollection.add(asistenciaCollectionAsistenciaToAttach);
            }
            medidor.setAsistenciaCollection(attachedAsistenciaCollection);
            Collection<Pagosnuevomed> attachedPagosnuevomedCollection = new ArrayList<Pagosnuevomed>();
            for (Pagosnuevomed pagosnuevomedCollectionPagosnuevomedToAttach : medidor.getPagosnuevomedCollection()) {
                pagosnuevomedCollectionPagosnuevomedToAttach = em.getReference(pagosnuevomedCollectionPagosnuevomedToAttach.getClass(), pagosnuevomedCollectionPagosnuevomedToAttach.getIdnuevomed());
                attachedPagosnuevomedCollection.add(pagosnuevomedCollectionPagosnuevomedToAttach);
            }
            medidor.setPagosnuevomedCollection(attachedPagosnuevomedCollection);
            Collection<Detallefactura> attachedDetallefacturaCollection = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaCollectionDetallefacturaToAttach : medidor.getDetallefacturaCollection()) {
                detallefacturaCollectionDetallefacturaToAttach = em.getReference(detallefacturaCollectionDetallefacturaToAttach.getClass(), detallefacturaCollectionDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaCollection.add(detallefacturaCollectionDetallefacturaToAttach);
            }
            medidor.setDetallefacturaCollection(attachedDetallefacturaCollection);
            em.persist(medidor);
            if (idusuario != null) {
                idusuario.getMedidorCollection().add(medidor);
                idusuario = em.merge(idusuario);
            }
            for (Usuarios usuariosCollectionUsuarios : medidor.getUsuariosCollection()) {
                usuariosCollectionUsuarios.getMedidorCollection().add(medidor);
                usuariosCollectionUsuarios = em.merge(usuariosCollectionUsuarios);
            }
            for (Asistenciapesillo asistenciapesilloCollectionAsistenciapesillo : medidor.getAsistenciapesilloCollection()) {
                Medidor oldIdmedidorOfAsistenciapesilloCollectionAsistenciapesillo = asistenciapesilloCollectionAsistenciapesillo.getIdmedidor();
                asistenciapesilloCollectionAsistenciapesillo.setIdmedidor(medidor);
                asistenciapesilloCollectionAsistenciapesillo = em.merge(asistenciapesilloCollectionAsistenciapesillo);
                if (oldIdmedidorOfAsistenciapesilloCollectionAsistenciapesillo != null) {
                    oldIdmedidorOfAsistenciapesilloCollectionAsistenciapesillo.getAsistenciapesilloCollection().remove(asistenciapesilloCollectionAsistenciapesillo);
                    oldIdmedidorOfAsistenciapesilloCollectionAsistenciapesillo = em.merge(oldIdmedidorOfAsistenciapesilloCollectionAsistenciapesillo);
                }
            }
            for (Corte corteCollectionCorte : medidor.getCorteCollection()) {
                Medidor oldIdmedidorOfCorteCollectionCorte = corteCollectionCorte.getIdmedidor();
                corteCollectionCorte.setIdmedidor(medidor);
                corteCollectionCorte = em.merge(corteCollectionCorte);
                if (oldIdmedidorOfCorteCollectionCorte != null) {
                    oldIdmedidorOfCorteCollectionCorte.getCorteCollection().remove(corteCollectionCorte);
                    oldIdmedidorOfCorteCollectionCorte = em.merge(oldIdmedidorOfCorteCollectionCorte);
                }
            }
            for (Asistencia asistenciaCollectionAsistencia : medidor.getAsistenciaCollection()) {
                Medidor oldIdmedidorOfAsistenciaCollectionAsistencia = asistenciaCollectionAsistencia.getIdmedidor();
                asistenciaCollectionAsistencia.setIdmedidor(medidor);
                asistenciaCollectionAsistencia = em.merge(asistenciaCollectionAsistencia);
                if (oldIdmedidorOfAsistenciaCollectionAsistencia != null) {
                    oldIdmedidorOfAsistenciaCollectionAsistencia.getAsistenciaCollection().remove(asistenciaCollectionAsistencia);
                    oldIdmedidorOfAsistenciaCollectionAsistencia = em.merge(oldIdmedidorOfAsistenciaCollectionAsistencia);
                }
            }
            for (Pagosnuevomed pagosnuevomedCollectionPagosnuevomed : medidor.getPagosnuevomedCollection()) {
                Medidor oldIdmedidorOfPagosnuevomedCollectionPagosnuevomed = pagosnuevomedCollectionPagosnuevomed.getIdmedidor();
                pagosnuevomedCollectionPagosnuevomed.setIdmedidor(medidor);
                pagosnuevomedCollectionPagosnuevomed = em.merge(pagosnuevomedCollectionPagosnuevomed);
                if (oldIdmedidorOfPagosnuevomedCollectionPagosnuevomed != null) {
                    oldIdmedidorOfPagosnuevomedCollectionPagosnuevomed.getPagosnuevomedCollection().remove(pagosnuevomedCollectionPagosnuevomed);
                    oldIdmedidorOfPagosnuevomedCollectionPagosnuevomed = em.merge(oldIdmedidorOfPagosnuevomedCollectionPagosnuevomed);
                }
            }
            for (Detallefactura detallefacturaCollectionDetallefactura : medidor.getDetallefacturaCollection()) {
                Medidor oldIdmedidorOfDetallefacturaCollectionDetallefactura = detallefacturaCollectionDetallefactura.getIdmedidor();
                detallefacturaCollectionDetallefactura.setIdmedidor(medidor);
                detallefacturaCollectionDetallefactura = em.merge(detallefacturaCollectionDetallefactura);
                if (oldIdmedidorOfDetallefacturaCollectionDetallefactura != null) {
                    oldIdmedidorOfDetallefacturaCollectionDetallefactura.getDetallefacturaCollection().remove(detallefacturaCollectionDetallefactura);
                    oldIdmedidorOfDetallefacturaCollectionDetallefactura = em.merge(oldIdmedidorOfDetallefacturaCollectionDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medidor medidor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidor persistentMedidor = em.find(Medidor.class, medidor.getIdmedidor());
            Usuarios idusuarioOld = persistentMedidor.getIdusuario();
            Usuarios idusuarioNew = medidor.getIdusuario();
            Collection<Usuarios> usuariosCollectionOld = persistentMedidor.getUsuariosCollection();
            Collection<Usuarios> usuariosCollectionNew = medidor.getUsuariosCollection();
            Collection<Asistenciapesillo> asistenciapesilloCollectionOld = persistentMedidor.getAsistenciapesilloCollection();
            Collection<Asistenciapesillo> asistenciapesilloCollectionNew = medidor.getAsistenciapesilloCollection();
            Collection<Corte> corteCollectionOld = persistentMedidor.getCorteCollection();
            Collection<Corte> corteCollectionNew = medidor.getCorteCollection();
            Collection<Asistencia> asistenciaCollectionOld = persistentMedidor.getAsistenciaCollection();
            Collection<Asistencia> asistenciaCollectionNew = medidor.getAsistenciaCollection();
            Collection<Pagosnuevomed> pagosnuevomedCollectionOld = persistentMedidor.getPagosnuevomedCollection();
            Collection<Pagosnuevomed> pagosnuevomedCollectionNew = medidor.getPagosnuevomedCollection();
            Collection<Detallefactura> detallefacturaCollectionOld = persistentMedidor.getDetallefacturaCollection();
            Collection<Detallefactura> detallefacturaCollectionNew = medidor.getDetallefacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (Asistenciapesillo asistenciapesilloCollectionOldAsistenciapesillo : asistenciapesilloCollectionOld) {
                if (!asistenciapesilloCollectionNew.contains(asistenciapesilloCollectionOldAsistenciapesillo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asistenciapesillo " + asistenciapesilloCollectionOldAsistenciapesillo + " since its idmedidor field is not nullable.");
                }
            }
            for (Corte corteCollectionOldCorte : corteCollectionOld) {
                if (!corteCollectionNew.contains(corteCollectionOldCorte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Corte " + corteCollectionOldCorte + " since its idmedidor field is not nullable.");
                }
            }
            for (Asistencia asistenciaCollectionOldAsistencia : asistenciaCollectionOld) {
                if (!asistenciaCollectionNew.contains(asistenciaCollectionOldAsistencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asistencia " + asistenciaCollectionOldAsistencia + " since its idmedidor field is not nullable.");
                }
            }
            for (Pagosnuevomed pagosnuevomedCollectionOldPagosnuevomed : pagosnuevomedCollectionOld) {
                if (!pagosnuevomedCollectionNew.contains(pagosnuevomedCollectionOldPagosnuevomed)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagosnuevomed " + pagosnuevomedCollectionOldPagosnuevomed + " since its idmedidor field is not nullable.");
                }
            }
            for (Detallefactura detallefacturaCollectionOldDetallefactura : detallefacturaCollectionOld) {
                if (!detallefacturaCollectionNew.contains(detallefacturaCollectionOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaCollectionOldDetallefactura + " since its idmedidor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                medidor.setIdusuario(idusuarioNew);
            }
            Collection<Usuarios> attachedUsuariosCollectionNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosCollectionNewUsuariosToAttach : usuariosCollectionNew) {
                usuariosCollectionNewUsuariosToAttach = em.getReference(usuariosCollectionNewUsuariosToAttach.getClass(), usuariosCollectionNewUsuariosToAttach.getIdusuario());
                attachedUsuariosCollectionNew.add(usuariosCollectionNewUsuariosToAttach);
            }
            usuariosCollectionNew = attachedUsuariosCollectionNew;
            medidor.setUsuariosCollection(usuariosCollectionNew);
            Collection<Asistenciapesillo> attachedAsistenciapesilloCollectionNew = new ArrayList<Asistenciapesillo>();
            for (Asistenciapesillo asistenciapesilloCollectionNewAsistenciapesilloToAttach : asistenciapesilloCollectionNew) {
                asistenciapesilloCollectionNewAsistenciapesilloToAttach = em.getReference(asistenciapesilloCollectionNewAsistenciapesilloToAttach.getClass(), asistenciapesilloCollectionNewAsistenciapesilloToAttach.getIdasistenciapesillo());
                attachedAsistenciapesilloCollectionNew.add(asistenciapesilloCollectionNewAsistenciapesilloToAttach);
            }
            asistenciapesilloCollectionNew = attachedAsistenciapesilloCollectionNew;
            medidor.setAsistenciapesilloCollection(asistenciapesilloCollectionNew);
            Collection<Corte> attachedCorteCollectionNew = new ArrayList<Corte>();
            for (Corte corteCollectionNewCorteToAttach : corteCollectionNew) {
                corteCollectionNewCorteToAttach = em.getReference(corteCollectionNewCorteToAttach.getClass(), corteCollectionNewCorteToAttach.getIdcorte());
                attachedCorteCollectionNew.add(corteCollectionNewCorteToAttach);
            }
            corteCollectionNew = attachedCorteCollectionNew;
            medidor.setCorteCollection(corteCollectionNew);
            Collection<Asistencia> attachedAsistenciaCollectionNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaCollectionNewAsistenciaToAttach : asistenciaCollectionNew) {
                asistenciaCollectionNewAsistenciaToAttach = em.getReference(asistenciaCollectionNewAsistenciaToAttach.getClass(), asistenciaCollectionNewAsistenciaToAttach.getIdasistencia());
                attachedAsistenciaCollectionNew.add(asistenciaCollectionNewAsistenciaToAttach);
            }
            asistenciaCollectionNew = attachedAsistenciaCollectionNew;
            medidor.setAsistenciaCollection(asistenciaCollectionNew);
            Collection<Pagosnuevomed> attachedPagosnuevomedCollectionNew = new ArrayList<Pagosnuevomed>();
            for (Pagosnuevomed pagosnuevomedCollectionNewPagosnuevomedToAttach : pagosnuevomedCollectionNew) {
                pagosnuevomedCollectionNewPagosnuevomedToAttach = em.getReference(pagosnuevomedCollectionNewPagosnuevomedToAttach.getClass(), pagosnuevomedCollectionNewPagosnuevomedToAttach.getIdnuevomed());
                attachedPagosnuevomedCollectionNew.add(pagosnuevomedCollectionNewPagosnuevomedToAttach);
            }
            pagosnuevomedCollectionNew = attachedPagosnuevomedCollectionNew;
            medidor.setPagosnuevomedCollection(pagosnuevomedCollectionNew);
            Collection<Detallefactura> attachedDetallefacturaCollectionNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaCollectionNewDetallefacturaToAttach : detallefacturaCollectionNew) {
                detallefacturaCollectionNewDetallefacturaToAttach = em.getReference(detallefacturaCollectionNewDetallefacturaToAttach.getClass(), detallefacturaCollectionNewDetallefacturaToAttach.getIddetallefac());
                attachedDetallefacturaCollectionNew.add(detallefacturaCollectionNewDetallefacturaToAttach);
            }
            detallefacturaCollectionNew = attachedDetallefacturaCollectionNew;
            medidor.setDetallefacturaCollection(detallefacturaCollectionNew);
            medidor = em.merge(medidor);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getMedidorCollection().remove(medidor);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getMedidorCollection().add(medidor);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Usuarios usuariosCollectionOldUsuarios : usuariosCollectionOld) {
                if (!usuariosCollectionNew.contains(usuariosCollectionOldUsuarios)) {
                    usuariosCollectionOldUsuarios.getMedidorCollection().remove(medidor);
                    usuariosCollectionOldUsuarios = em.merge(usuariosCollectionOldUsuarios);
                }
            }
            for (Usuarios usuariosCollectionNewUsuarios : usuariosCollectionNew) {
                if (!usuariosCollectionOld.contains(usuariosCollectionNewUsuarios)) {
                    usuariosCollectionNewUsuarios.getMedidorCollection().add(medidor);
                    usuariosCollectionNewUsuarios = em.merge(usuariosCollectionNewUsuarios);
                }
            }
            for (Asistenciapesillo asistenciapesilloCollectionNewAsistenciapesillo : asistenciapesilloCollectionNew) {
                if (!asistenciapesilloCollectionOld.contains(asistenciapesilloCollectionNewAsistenciapesillo)) {
                    Medidor oldIdmedidorOfAsistenciapesilloCollectionNewAsistenciapesillo = asistenciapesilloCollectionNewAsistenciapesillo.getIdmedidor();
                    asistenciapesilloCollectionNewAsistenciapesillo.setIdmedidor(medidor);
                    asistenciapesilloCollectionNewAsistenciapesillo = em.merge(asistenciapesilloCollectionNewAsistenciapesillo);
                    if (oldIdmedidorOfAsistenciapesilloCollectionNewAsistenciapesillo != null && !oldIdmedidorOfAsistenciapesilloCollectionNewAsistenciapesillo.equals(medidor)) {
                        oldIdmedidorOfAsistenciapesilloCollectionNewAsistenciapesillo.getAsistenciapesilloCollection().remove(asistenciapesilloCollectionNewAsistenciapesillo);
                        oldIdmedidorOfAsistenciapesilloCollectionNewAsistenciapesillo = em.merge(oldIdmedidorOfAsistenciapesilloCollectionNewAsistenciapesillo);
                    }
                }
            }
            for (Corte corteCollectionNewCorte : corteCollectionNew) {
                if (!corteCollectionOld.contains(corteCollectionNewCorte)) {
                    Medidor oldIdmedidorOfCorteCollectionNewCorte = corteCollectionNewCorte.getIdmedidor();
                    corteCollectionNewCorte.setIdmedidor(medidor);
                    corteCollectionNewCorte = em.merge(corteCollectionNewCorte);
                    if (oldIdmedidorOfCorteCollectionNewCorte != null && !oldIdmedidorOfCorteCollectionNewCorte.equals(medidor)) {
                        oldIdmedidorOfCorteCollectionNewCorte.getCorteCollection().remove(corteCollectionNewCorte);
                        oldIdmedidorOfCorteCollectionNewCorte = em.merge(oldIdmedidorOfCorteCollectionNewCorte);
                    }
                }
            }
            for (Asistencia asistenciaCollectionNewAsistencia : asistenciaCollectionNew) {
                if (!asistenciaCollectionOld.contains(asistenciaCollectionNewAsistencia)) {
                    Medidor oldIdmedidorOfAsistenciaCollectionNewAsistencia = asistenciaCollectionNewAsistencia.getIdmedidor();
                    asistenciaCollectionNewAsistencia.setIdmedidor(medidor);
                    asistenciaCollectionNewAsistencia = em.merge(asistenciaCollectionNewAsistencia);
                    if (oldIdmedidorOfAsistenciaCollectionNewAsistencia != null && !oldIdmedidorOfAsistenciaCollectionNewAsistencia.equals(medidor)) {
                        oldIdmedidorOfAsistenciaCollectionNewAsistencia.getAsistenciaCollection().remove(asistenciaCollectionNewAsistencia);
                        oldIdmedidorOfAsistenciaCollectionNewAsistencia = em.merge(oldIdmedidorOfAsistenciaCollectionNewAsistencia);
                    }
                }
            }
            for (Pagosnuevomed pagosnuevomedCollectionNewPagosnuevomed : pagosnuevomedCollectionNew) {
                if (!pagosnuevomedCollectionOld.contains(pagosnuevomedCollectionNewPagosnuevomed)) {
                    Medidor oldIdmedidorOfPagosnuevomedCollectionNewPagosnuevomed = pagosnuevomedCollectionNewPagosnuevomed.getIdmedidor();
                    pagosnuevomedCollectionNewPagosnuevomed.setIdmedidor(medidor);
                    pagosnuevomedCollectionNewPagosnuevomed = em.merge(pagosnuevomedCollectionNewPagosnuevomed);
                    if (oldIdmedidorOfPagosnuevomedCollectionNewPagosnuevomed != null && !oldIdmedidorOfPagosnuevomedCollectionNewPagosnuevomed.equals(medidor)) {
                        oldIdmedidorOfPagosnuevomedCollectionNewPagosnuevomed.getPagosnuevomedCollection().remove(pagosnuevomedCollectionNewPagosnuevomed);
                        oldIdmedidorOfPagosnuevomedCollectionNewPagosnuevomed = em.merge(oldIdmedidorOfPagosnuevomedCollectionNewPagosnuevomed);
                    }
                }
            }
            for (Detallefactura detallefacturaCollectionNewDetallefactura : detallefacturaCollectionNew) {
                if (!detallefacturaCollectionOld.contains(detallefacturaCollectionNewDetallefactura)) {
                    Medidor oldIdmedidorOfDetallefacturaCollectionNewDetallefactura = detallefacturaCollectionNewDetallefactura.getIdmedidor();
                    detallefacturaCollectionNewDetallefactura.setIdmedidor(medidor);
                    detallefacturaCollectionNewDetallefactura = em.merge(detallefacturaCollectionNewDetallefactura);
                    if (oldIdmedidorOfDetallefacturaCollectionNewDetallefactura != null && !oldIdmedidorOfDetallefacturaCollectionNewDetallefactura.equals(medidor)) {
                        oldIdmedidorOfDetallefacturaCollectionNewDetallefactura.getDetallefacturaCollection().remove(detallefacturaCollectionNewDetallefactura);
                        oldIdmedidorOfDetallefacturaCollectionNewDetallefactura = em.merge(oldIdmedidorOfDetallefacturaCollectionNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medidor.getIdmedidor();
                if (findMedidor(id) == null) {
                    throw new NonexistentEntityException("The medidor with id " + id + " no longer exists.");
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
            Medidor medidor;
            try {
                medidor = em.getReference(Medidor.class, id);
                medidor.getIdmedidor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medidor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Asistenciapesillo> asistenciapesilloCollectionOrphanCheck = medidor.getAsistenciapesilloCollection();
            for (Asistenciapesillo asistenciapesilloCollectionOrphanCheckAsistenciapesillo : asistenciapesilloCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medidor (" + medidor + ") cannot be destroyed since the Asistenciapesillo " + asistenciapesilloCollectionOrphanCheckAsistenciapesillo + " in its asistenciapesilloCollection field has a non-nullable idmedidor field.");
            }
            Collection<Corte> corteCollectionOrphanCheck = medidor.getCorteCollection();
            for (Corte corteCollectionOrphanCheckCorte : corteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medidor (" + medidor + ") cannot be destroyed since the Corte " + corteCollectionOrphanCheckCorte + " in its corteCollection field has a non-nullable idmedidor field.");
            }
            Collection<Asistencia> asistenciaCollectionOrphanCheck = medidor.getAsistenciaCollection();
            for (Asistencia asistenciaCollectionOrphanCheckAsistencia : asistenciaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medidor (" + medidor + ") cannot be destroyed since the Asistencia " + asistenciaCollectionOrphanCheckAsistencia + " in its asistenciaCollection field has a non-nullable idmedidor field.");
            }
            Collection<Pagosnuevomed> pagosnuevomedCollectionOrphanCheck = medidor.getPagosnuevomedCollection();
            for (Pagosnuevomed pagosnuevomedCollectionOrphanCheckPagosnuevomed : pagosnuevomedCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medidor (" + medidor + ") cannot be destroyed since the Pagosnuevomed " + pagosnuevomedCollectionOrphanCheckPagosnuevomed + " in its pagosnuevomedCollection field has a non-nullable idmedidor field.");
            }
            Collection<Detallefactura> detallefacturaCollectionOrphanCheck = medidor.getDetallefacturaCollection();
            for (Detallefactura detallefacturaCollectionOrphanCheckDetallefactura : detallefacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medidor (" + medidor + ") cannot be destroyed since the Detallefactura " + detallefacturaCollectionOrphanCheckDetallefactura + " in its detallefacturaCollection field has a non-nullable idmedidor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuarios idusuario = medidor.getIdusuario();
            if (idusuario != null) {
                idusuario.getMedidorCollection().remove(medidor);
                idusuario = em.merge(idusuario);
            }
            Collection<Usuarios> usuariosCollection = medidor.getUsuariosCollection();
            for (Usuarios usuariosCollectionUsuarios : usuariosCollection) {
                usuariosCollectionUsuarios.getMedidorCollection().remove(medidor);
                usuariosCollectionUsuarios = em.merge(usuariosCollectionUsuarios);
            }
            em.remove(medidor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medidor> findMedidorEntities() {
        return findMedidorEntities(true, -1, -1);
    }

    public List<Medidor> findMedidorEntities(int maxResults, int firstResult) {
        return findMedidorEntities(false, maxResults, firstResult);
    }

    private List<Medidor> findMedidorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medidor.class));
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

    public Medidor findMedidor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medidor.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedidorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medidor> rt = cq.from(Medidor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
