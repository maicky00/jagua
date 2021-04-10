/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tech-Usuario
 */
@Entity
@Table(name = "medidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medidor.findAll", query = "SELECT m FROM Medidor m"),
    @NamedQuery(name = "Medidor.findByIdmedidor", query = "SELECT m FROM Medidor m WHERE m.idmedidor = :idmedidor"),
    @NamedQuery(name = "Medidor.findBySerie", query = "SELECT m FROM Medidor m WHERE m.serie = :serie"),
    @NamedQuery(name = "Medidor.findByNummedidor", query = "SELECT m FROM Medidor m WHERE m.nummedidor = :nummedidor"),
    @NamedQuery(name = "Medidor.findByEstado", query = "SELECT m FROM Medidor m WHERE m.estado = :estado"),
    @NamedQuery(name = "Medidor.findByValorporconexion", query = "SELECT m FROM Medidor m WHERE m.valorporconexion = :valorporconexion"),
    @NamedQuery(name = "Medidor.findByPagado", query = "SELECT m FROM Medidor m WHERE m.pagado = :pagado"),
    @NamedQuery(name = "Medidor.findBySaldo", query = "SELECT m FROM Medidor m WHERE m.saldo = :saldo"),
    @NamedQuery(name = "Medidor.findByFecha", query = "SELECT m FROM Medidor m WHERE m.fecha = :fecha")})
public class Medidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMEDIDOR")
    private Integer idmedidor;
    @Column(name = "SERIE")
    private String serie;
    @Column(name = "NUMMEDIDOR")
    private Integer nummedidor;
    @Column(name = "ESTADO")
    private String estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORPORCONEXION")
    private Float valorporconexion;
    @Column(name = "PAGADO")
    private String pagado;
    @Column(name = "SALDO")
    private Float saldo;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @ManyToMany(mappedBy = "medidorCollection")
    private Collection<Usuarios> usuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedidor")
    private Collection<Asistenciapesillo> asistenciapesilloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedidor")
    private Collection<Corte> corteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedidor")
    private Collection<Asistencia> asistenciaCollection;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuarios idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedidor")
    private Collection<Pagosnuevomed> pagosnuevomedCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedidor")
    private Collection<Detallefactura> detallefacturaCollection;

    public Medidor() {
    }

    public Medidor(Integer idmedidor) {
        this.idmedidor = idmedidor;
    }

    public Integer getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Integer idmedidor) {
        this.idmedidor = idmedidor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNummedidor() {
        return nummedidor;
    }

    public void setNummedidor(Integer nummedidor) {
        this.nummedidor = nummedidor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Float getValorporconexion() {
        return valorporconexion;
    }

    public void setValorporconexion(Float valorporconexion) {
        this.valorporconexion = valorporconexion;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @XmlTransient
    public Collection<Asistenciapesillo> getAsistenciapesilloCollection() {
        return asistenciapesilloCollection;
    }

    public void setAsistenciapesilloCollection(Collection<Asistenciapesillo> asistenciapesilloCollection) {
        this.asistenciapesilloCollection = asistenciapesilloCollection;
    }

    @XmlTransient
    public Collection<Corte> getCorteCollection() {
        return corteCollection;
    }

    public void setCorteCollection(Collection<Corte> corteCollection) {
        this.corteCollection = corteCollection;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<Pagosnuevomed> getPagosnuevomedCollection() {
        return pagosnuevomedCollection;
    }

    public void setPagosnuevomedCollection(Collection<Pagosnuevomed> pagosnuevomedCollection) {
        this.pagosnuevomedCollection = pagosnuevomedCollection;
    }

    @XmlTransient
    public Collection<Detallefactura> getDetallefacturaCollection() {
        return detallefacturaCollection;
    }

    public void setDetallefacturaCollection(Collection<Detallefactura> detallefacturaCollection) {
        this.detallefacturaCollection = detallefacturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedidor != null ? idmedidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medidor)) {
            return false;
        }
        Medidor other = (Medidor) object;
        if ((this.idmedidor == null && other.idmedidor != null) || (this.idmedidor != null && !this.idmedidor.equals(other.idmedidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Medidor[ idmedidor=" + idmedidor + " ]";
    }
    
}
