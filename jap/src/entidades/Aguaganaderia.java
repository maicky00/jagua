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
@Table(name = "aguaganaderia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aguaganaderia.findAll", query = "SELECT a FROM Aguaganaderia a"),
    @NamedQuery(name = "Aguaganaderia.findByIdaguaganaderia", query = "SELECT a FROM Aguaganaderia a WHERE a.idaguaganaderia = :idaguaganaderia"),
    @NamedQuery(name = "Aguaganaderia.findBySector", query = "SELECT a FROM Aguaganaderia a WHERE a.sector = :sector"),
    @NamedQuery(name = "Aguaganaderia.findByReferencia", query = "SELECT a FROM Aguaganaderia a WHERE a.referencia = :referencia"),
    @NamedQuery(name = "Aguaganaderia.findByCodigoaguaganaderia", query = "SELECT a FROM Aguaganaderia a WHERE a.codigoaguaganaderia = :codigoaguaganaderia"),
    @NamedQuery(name = "Aguaganaderia.findByObservacion", query = "SELECT a FROM Aguaganaderia a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "Aguaganaderia.findByEstado", query = "SELECT a FROM Aguaganaderia a WHERE a.estado = :estado"),
    @NamedQuery(name = "Aguaganaderia.findByValorporconexion", query = "SELECT a FROM Aguaganaderia a WHERE a.valorporconexion = :valorporconexion"),
    @NamedQuery(name = "Aguaganaderia.findByPagado", query = "SELECT a FROM Aguaganaderia a WHERE a.pagado = :pagado"),
    @NamedQuery(name = "Aguaganaderia.findBySaldo", query = "SELECT a FROM Aguaganaderia a WHERE a.saldo = :saldo"),
    @NamedQuery(name = "Aguaganaderia.findByFecha", query = "SELECT a FROM Aguaganaderia a WHERE a.fecha = :fecha")})
public class Aguaganaderia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDAGUAGANADERIA")
    private Integer idaguaganaderia;
    @Column(name = "SECTOR")
    private String sector;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "CODIGOAGUAGANADERIA")
    private Integer codigoaguaganaderia;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idaguaganaderia")
    private Collection<Detallefacturaganaderia> detallefacturaganaderiaCollection;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuarios idusuario;

    public Aguaganaderia() {
    }

    public Aguaganaderia(Integer idaguaganaderia) {
        this.idaguaganaderia = idaguaganaderia;
    }

    public Integer getIdaguaganaderia() {
        return idaguaganaderia;
    }

    public void setIdaguaganaderia(Integer idaguaganaderia) {
        this.idaguaganaderia = idaguaganaderia;
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

    public Integer getCodigoaguaganaderia() {
        return codigoaguaganaderia;
    }

    public void setCodigoaguaganaderia(Integer codigoaguaganaderia) {
        this.codigoaguaganaderia = codigoaguaganaderia;
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

    @XmlTransient
    public Collection<Detallefacturaganaderia> getDetallefacturaganaderiaCollection() {
        return detallefacturaganaderiaCollection;
    }

    public void setDetallefacturaganaderiaCollection(Collection<Detallefacturaganaderia> detallefacturaganaderiaCollection) {
        this.detallefacturaganaderiaCollection = detallefacturaganaderiaCollection;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaguaganaderia != null ? idaguaganaderia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aguaganaderia)) {
            return false;
        }
        Aguaganaderia other = (Aguaganaderia) object;
        if ((this.idaguaganaderia == null && other.idaguaganaderia != null) || (this.idaguaganaderia != null && !this.idaguaganaderia.equals(other.idaguaganaderia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Aguaganaderia[ idaguaganaderia=" + idaguaganaderia + " ]";
    }
    
}
