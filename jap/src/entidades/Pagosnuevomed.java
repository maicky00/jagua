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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tech-Usuario
 */
@Entity
@Table(name = "pagosnuevomed")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagosnuevomed.findAll", query = "SELECT p FROM Pagosnuevomed p"),
    @NamedQuery(name = "Pagosnuevomed.findByIdnuevomed", query = "SELECT p FROM Pagosnuevomed p WHERE p.idnuevomed = :idnuevomed"),
    @NamedQuery(name = "Pagosnuevomed.findByObsercion", query = "SELECT p FROM Pagosnuevomed p WHERE p.obsercion = :obsercion"),
    @NamedQuery(name = "Pagosnuevomed.findByCantidadpagar", query = "SELECT p FROM Pagosnuevomed p WHERE p.cantidadpagar = :cantidadpagar"),
    @NamedQuery(name = "Pagosnuevomed.findByFechapago", query = "SELECT p FROM Pagosnuevomed p WHERE p.fechapago = :fechapago"),
    @NamedQuery(name = "Pagosnuevomed.findByNumfact", query = "SELECT p FROM Pagosnuevomed p WHERE p.numfact = :numfact")})
public class Pagosnuevomed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDNUEVOMED")
    private Integer idnuevomed;
    @Column(name = "OBSERCION")
    private String obsercion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDADPAGAR")
    private Float cantidadpagar;
    @Column(name = "FECHAPAGO")
    @Temporal(TemporalType.DATE)
    private Date fechapago;
    @Column(name = "NUMFACT")
    private Integer numfact;
    @JoinColumn(name = "IDMEDIDOR", referencedColumnName = "IDMEDIDOR")
    @ManyToOne(optional = false)
    private Medidor idmedidor;

    public Pagosnuevomed() {
    }

    public Pagosnuevomed(Integer idnuevomed) {
        this.idnuevomed = idnuevomed;
    }

    public Integer getIdnuevomed() {
        return idnuevomed;
    }

    public void setIdnuevomed(Integer idnuevomed) {
        this.idnuevomed = idnuevomed;
    }

    public String getObsercion() {
        return obsercion;
    }

    public void setObsercion(String obsercion) {
        this.obsercion = obsercion;
    }

    public Float getCantidadpagar() {
        return cantidadpagar;
    }

    public void setCantidadpagar(Float cantidadpagar) {
        this.cantidadpagar = cantidadpagar;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Integer getNumfact() {
        return numfact;
    }

    public void setNumfact(Integer numfact) {
        this.numfact = numfact;
    }

    public Medidor getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Medidor idmedidor) {
        this.idmedidor = idmedidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnuevomed != null ? idnuevomed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagosnuevomed)) {
            return false;
        }
        Pagosnuevomed other = (Pagosnuevomed) object;
        if ((this.idnuevomed == null && other.idnuevomed != null) || (this.idnuevomed != null && !this.idnuevomed.equals(other.idnuevomed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pagosnuevomed[ idnuevomed=" + idnuevomed + " ]";
    }
    
}
