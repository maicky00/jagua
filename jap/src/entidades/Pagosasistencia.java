/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JC-PC
 */
@Entity
@Table(name = "pagosasistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagosasistencia.findAll", query = "SELECT p FROM Pagosasistencia p"),
    @NamedQuery(name = "Pagosasistencia.findByIdpagoasistencia", query = "SELECT p FROM Pagosasistencia p WHERE p.idpagoasistencia = :idpagoasistencia"),
    @NamedQuery(name = "Pagosasistencia.findByFechapago", query = "SELECT p FROM Pagosasistencia p WHERE p.fechapago = :fechapago"),
    @NamedQuery(name = "Pagosasistencia.findByNummingas", query = "SELECT p FROM Pagosasistencia p WHERE p.nummingas = :nummingas"),
    @NamedQuery(name = "Pagosasistencia.findByValormingas", query = "SELECT p FROM Pagosasistencia p WHERE p.valormingas = :valormingas"),
    @NamedQuery(name = "Pagosasistencia.findByObservacion", query = "SELECT p FROM Pagosasistencia p WHERE p.observacion = :observacion")})
public class Pagosasistencia implements Serializable {
    @Column(name = "NUMFACTURA")
    private Integer numfactura;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPAGOASISTENCIA")
    private Integer idpagoasistencia;
    @Column(name = "FECHAPAGO")
    @Temporal(TemporalType.DATE)
    private Date fechapago;
    @Column(name = "NUMMINGAS")
    private Integer nummingas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORMINGAS")
    private Float valormingas;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Lob
    @Column(name = "USUARIOACTUAL")
    private String usuarioactual;
    @JoinColumn(name = "IDASISTENCIA", referencedColumnName = "IDASISTENCIA")
    @ManyToOne
    private Asistencia idasistencia;

    public Pagosasistencia() {
    }

    public Pagosasistencia(Integer idpagoasistencia) {
        this.idpagoasistencia = idpagoasistencia;
    }

    public Integer getIdpagoasistencia() {
        return idpagoasistencia;
    }

    public void setIdpagoasistencia(Integer idpagoasistencia) {
        this.idpagoasistencia = idpagoasistencia;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Integer getNummingas() {
        return nummingas;
    }

    public void setNummingas(Integer nummingas) {
        this.nummingas = nummingas;
    }

    public Float getValormingas() {
        return valormingas;
    }

    public void setValormingas(Float valormingas) {
        this.valormingas = valormingas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuarioactual() {
        return usuarioactual;
    }

    public void setUsuarioactual(String usuarioactual) {
        this.usuarioactual = usuarioactual;
    }

    public Asistencia getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(Asistencia idasistencia) {
        this.idasistencia = idasistencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagoasistencia != null ? idpagoasistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagosasistencia)) {
            return false;
        }
        Pagosasistencia other = (Pagosasistencia) object;
        if ((this.idpagoasistencia == null && other.idpagoasistencia != null) || (this.idpagoasistencia != null && !this.idpagoasistencia.equals(other.idpagoasistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pagosasistencia[ idpagoasistencia=" + idpagoasistencia + " ]";
    }

    public Integer getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(Integer numfactura) {
        this.numfactura = numfactura;
    }
    
}
