/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tech-Usuario
 */
@Entity
@Table(name = "institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i"),
    @NamedQuery(name = "Institucion.findByIdinstitucion", query = "SELECT i FROM Institucion i WHERE i.idinstitucion = :idinstitucion"),
    @NamedQuery(name = "Institucion.findByTelefono", query = "SELECT i FROM Institucion i WHERE i.telefono = :telefono"),
    @NamedQuery(name = "Institucion.findByEmail", query = "SELECT i FROM Institucion i WHERE i.email = :email"),
    @NamedQuery(name = "Institucion.findByRuc", query = "SELECT i FROM Institucion i WHERE i.ruc = :ruc"),
    @NamedQuery(name = "Institucion.findByCelular", query = "SELECT i FROM Institucion i WHERE i.celular = :celular")})
public class Institucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDINSTITUCION")
    private Integer idinstitucion;
    @Lob
    @Column(name = "NOMBREINST")
    private String nombreinst;
    @Lob
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "RUC")
    private String ruc;
    @Column(name = "CELULAR")
    private String celular;
    @Lob
    @Column(name = "LOGO")
    private byte[] logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idinstitucion")
    private Collection<Usuarios> usuariosCollection;

    public Institucion() {
    }

    public Institucion(Integer idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public Integer getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(Integer idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public String getNombreinst() {
        return nombreinst;
    }

    public void setNombreinst(String nombreinst) {
        this.nombreinst = nombreinst;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinstitucion != null ? idinstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.idinstitucion == null && other.idinstitucion != null) || (this.idinstitucion != null && !this.idinstitucion.equals(other.idinstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Institucion[ idinstitucion=" + idinstitucion + " ]";
    }
    
}
