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
@Table(name = "aguasobrante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aguasobrante.findAll", query = "SELECT a FROM Aguasobrante a"),
    @NamedQuery(name = "Aguasobrante.findByIdaguasobrante", query = "SELECT a FROM Aguasobrante a WHERE a.idaguasobrante = :idaguasobrante"),
    @NamedQuery(name = "Aguasobrante.findBySector", query = "SELECT a FROM Aguasobrante a WHERE a.sector = :sector"),
    @NamedQuery(name = "Aguasobrante.findByReferencia", query = "SELECT a FROM Aguasobrante a WHERE a.referencia = :referencia"),
    @NamedQuery(name = "Aguasobrante.findByCodigoaguasobrante", query = "SELECT a FROM Aguasobrante a WHERE a.codigoaguasobrante = :codigoaguasobrante"),
    @NamedQuery(name = "Aguasobrante.findByObservacion", query = "SELECT a FROM Aguasobrante a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "Aguasobrante.findByEstado", query = "SELECT a FROM Aguasobrante a WHERE a.estado = :estado"),
    @NamedQuery(name = "Aguasobrante.findByValorporconexion", query = "SELECT a FROM Aguasobrante a WHERE a.valorporconexion = :valorporconexion"),
    @NamedQuery(name = "Aguasobrante.findByPagado", query = "SELECT a FROM Aguasobrante a WHERE a.pagado = :pagado"),
    @NamedQuery(name = "Aguasobrante.findBySaldo", query = "SELECT a FROM Aguasobrante a WHERE a.saldo = :saldo"),
    @NamedQuery(name = "Aguasobrante.findByFecha", query = "SELECT a FROM Aguasobrante a WHERE a.fecha = :fecha")})
public class Aguasobrante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDAGUASOBRANTE")
    private Integer idaguasobrante;
    @Column(name = "SECTOR")
    private String sector;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "CODIGOAGUASOBRANTE")
    private Integer codigoaguasobrante;
    @Column(name = "OBSERVACION")
    private String observacion;
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
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuarios idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaguasobrante")
    private Collection<Detallefacturasobrante> detallefacturasobranteCollection;

    public Aguasobrante() {
    }

    public Aguasobrante(Integer idaguasobrante) {
        this.idaguasobrante = idaguasobrante;
    }

    public Integer getIdaguasobrante() {
        return idaguasobrante;
    }

    public void setIdaguasobrante(Integer idaguasobrante) {
        this.idaguasobrante = idaguasobrante;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getCodigoaguasobrante() {
        return codigoaguasobrante;
    }

    public void setCodigoaguasobrante(Integer codigoaguasobrante) {
        this.codigoaguasobrante = codigoaguasobrante;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public Collection<Detallefacturasobrante> getDetallefacturasobranteCollection() {
        return detallefacturasobranteCollection;
    }

    public void setDetallefacturasobranteCollection(Collection<Detallefacturasobrante> detallefacturasobranteCollection) {
        this.detallefacturasobranteCollection = detallefacturasobranteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaguasobrante != null ? idaguasobrante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aguasobrante)) {
            return false;
        }
        Aguasobrante other = (Aguasobrante) object;
        if ((this.idaguasobrante == null && other.idaguasobrante != null) || (this.idaguasobrante != null && !this.idaguasobrante.equals(other.idaguasobrante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Aguasobrante[ idaguasobrante=" + idaguasobrante + " ]";
    }
    
}
