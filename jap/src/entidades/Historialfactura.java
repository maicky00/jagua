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
@Table(name = "historialfactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historialfactura.findAll", query = "SELECT h FROM Historialfactura h"),
    @NamedQuery(name = "Historialfactura.findById", query = "SELECT h FROM Historialfactura h WHERE h.id = :id"),
    @NamedQuery(name = "Historialfactura.findByIdUsuario", query = "SELECT h FROM Historialfactura h WHERE h.idUsuario = :idUsuario"),
    @NamedQuery(name = "Historialfactura.findByNumFact", query = "SELECT h FROM Historialfactura h WHERE h.numFact = :numFact"),
    @NamedQuery(name = "Historialfactura.findByFecha", query = "SELECT h FROM Historialfactura h WHERE h.fecha = :fecha")})
public class Historialfactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Column(name = "numFact")
    private Integer numFact;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Historialfactura() {
    }

    public Historialfactura(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getNumFact() {
        return numFact;
    }

    public void setNumFact(Integer numFact) {
        this.numFact = numFact;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialfactura)) {
            return false;
        }
        Historialfactura other = (Historialfactura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Historialfactura[ id=" + id + " ]";
    }
    
}
