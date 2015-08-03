/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
 * @author JC-PC
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdusuario", query = "SELECT u FROM Usuarios u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuarios.findByRucci", query = "SELECT u FROM Usuarios u WHERE u.rucci = :rucci"),
    @NamedQuery(name = "Usuarios.findByPrimernombre", query = "SELECT u FROM Usuarios u WHERE u.primernombre = :primernombre"),
    @NamedQuery(name = "Usuarios.findBySegundonombre", query = "SELECT u FROM Usuarios u WHERE u.segundonombre = :segundonombre"),
    @NamedQuery(name = "Usuarios.findByPrimerapellido", query = "SELECT u FROM Usuarios u WHERE u.primerapellido = :primerapellido"),
    @NamedQuery(name = "Usuarios.findByApadosn", query = "SELECT u FROM Usuarios u WHERE u.apadosn = :apadosn"),
    @NamedQuery(name = "Usuarios.findByDireccion", query = "SELECT u FROM Usuarios u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuarios.findByCelular", query = "SELECT u FROM Usuarios u WHERE u.celular = :celular"),
    @NamedQuery(name = "Usuarios.findBySector", query = "SELECT u FROM Usuarios u WHERE u.sector = :sector"),
    @NamedQuery(name = "Usuarios.findByReferencia", query = "SELECT u FROM Usuarios u WHERE u.referencia = :referencia")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
    @Column(name = "RUCCI")
    private String rucci;
    @Column(name = "PRIMERNOMBRE")
    private String primernombre;
    @Column(name = "SEGUNDONOMBRE")
    private String segundonombre;
    @Column(name = "PRIMERAPELLIDO")
    private String primerapellido;
    @Column(name = "APADOSN")
    private String apadosn;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CELULAR")
    private String celular;
    @Column(name = "SECTOR")
    private String sector;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Lob
    @Column(name = "FOTO")
    private byte[] foto;
    @Lob
    @Column(name = "OBSERVACION")
    private String observacion;
    @OneToMany(mappedBy = "idusuario")
    private List<Medidor> medidorList;

    public Usuarios() {
    }

    public Usuarios(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getRucci() {
        return rucci;
    }

    public void setRucci(String rucci) {
        this.rucci = rucci;
    }

    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getApadosn() {
        return apadosn;
    }

    public void setApadosn(String apadosn) {
        this.apadosn = apadosn;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    public List<Medidor> getMedidorList() {
        return medidorList;
    }

    public void setMedidorList(List<Medidor> medidorList) {
        this.medidorList = medidorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuarios[ idusuario=" + idusuario + " ]";
    }
    
}
