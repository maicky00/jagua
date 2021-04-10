/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tech-Usuario
 */
@Entity
@Table(name = "otrosconceptos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Otrosconceptos.findAll", query = "SELECT o FROM Otrosconceptos o"),
    @NamedQuery(name = "Otrosconceptos.findById", query = "SELECT o FROM Otrosconceptos o WHERE o.id = :id"),
    @NamedQuery(name = "Otrosconceptos.findByDescripcion", query = "SELECT o FROM Otrosconceptos o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Otrosconceptos.findByCantidad", query = "SELECT o FROM Otrosconceptos o WHERE o.cantidad = :cantidad"),
    @NamedQuery(name = "Otrosconceptos.findByTiempo", query = "SELECT o FROM Otrosconceptos o WHERE o.tiempo = :tiempo"),
    @NamedQuery(name = "Otrosconceptos.findByActivo", query = "SELECT o FROM Otrosconceptos o WHERE o.activo = :activo")})
public class Otrosconceptos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private Float cantidad;
    @Column(name = "TIEMPO")
    private Integer tiempo;
    @Column(name = "ACTIVO")
    private String activo;

    public Otrosconceptos() {
    }

    public Otrosconceptos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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
        if (!(object instanceof Otrosconceptos)) {
            return false;
        }
        Otrosconceptos other = (Otrosconceptos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Otrosconceptos[ id=" + id + " ]";
    }
    
}
