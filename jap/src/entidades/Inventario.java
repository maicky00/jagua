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
import javax.persistence.Lob;
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
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdinventario", query = "SELECT i FROM Inventario i WHERE i.idinventario = :idinventario"),
    @NamedQuery(name = "Inventario.findByCodarticulo", query = "SELECT i FROM Inventario i WHERE i.codarticulo = :codarticulo"),
    @NamedQuery(name = "Inventario.findByFechaadquisicion", query = "SELECT i FROM Inventario i WHERE i.fechaadquisicion = :fechaadquisicion"),
    @NamedQuery(name = "Inventario.findByValor", query = "SELECT i FROM Inventario i WHERE i.valor = :valor"),
    @NamedQuery(name = "Inventario.findByDepreciable", query = "SELECT i FROM Inventario i WHERE i.depreciable = :depreciable"),
    @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad")})
public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDINVENTARIO")
    private Integer idinventario;
    @Column(name = "CODARTICULO")
    private String codarticulo;
    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHAADQUISICION")
    @Temporal(TemporalType.DATE)
    private Date fechaadquisicion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private Float valor;
    @Column(name = "DEPRECIABLE")
    private String depreciable;
    @Lob
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Lob
    @Column(name = "IMAGEN")
    private byte[] imagen;

    public Inventario() {
    }

    public Inventario(Integer idinventario) {
        this.idinventario = idinventario;
    }

    public Integer getIdinventario() {
        return idinventario;
    }

    public void setIdinventario(Integer idinventario) {
        this.idinventario = idinventario;
    }

    public String getCodarticulo() {
        return codarticulo;
    }

    public void setCodarticulo(String codarticulo) {
        this.codarticulo = codarticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaadquisicion() {
        return fechaadquisicion;
    }

    public void setFechaadquisicion(Date fechaadquisicion) {
        this.fechaadquisicion = fechaadquisicion;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDepreciable() {
        return depreciable;
    }

    public void setDepreciable(String depreciable) {
        this.depreciable = depreciable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinventario != null ? idinventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idinventario == null && other.idinventario != null) || (this.idinventario != null && !this.idinventario.equals(other.idinventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Inventario[ idinventario=" + idinventario + " ]";
    }
    
}
