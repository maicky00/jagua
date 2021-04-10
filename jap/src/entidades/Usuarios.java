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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdusuario", query = "SELECT u FROM Usuarios u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuarios.findByRucci", query = "SELECT u FROM Usuarios u WHERE u.rucci = :rucci"),
    @NamedQuery(name = "Usuarios.findByPrimernombre", query = "SELECT u FROM Usuarios u WHERE u.primernombre = :primernombre"),
    @NamedQuery(name = "Usuarios.findBySegundonombre", query = "SELECT u FROM Usuarios u WHERE u.segundonombre = :segundonombre"),
    @NamedQuery(name = "Usuarios.findByPrimerapellido", query = "SELECT u FROM Usuarios u WHERE u.primerapellido = :primerapellido"),
    @NamedQuery(name = "Usuarios.findBySegundoapellido", query = "SELECT u FROM Usuarios u WHERE u.segundoapellido = :segundoapellido"),
    @NamedQuery(name = "Usuarios.findByApadosn", query = "SELECT u FROM Usuarios u WHERE u.apadosn = :apadosn"),
    @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuarios.findByCelular", query = "SELECT u FROM Usuarios u WHERE u.celular = :celular"),
    @NamedQuery(name = "Usuarios.findByEstado", query = "SELECT u FROM Usuarios u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuarios.findByVisto", query = "SELECT u FROM Usuarios u WHERE u.visto = :visto"),
    @NamedQuery(name = "Usuarios.findByEstadoMedidor", query = "SELECT u FROM Usuarios u WHERE u.estadoMedidor = :estadoMedidor"),
    @NamedQuery(name = "Usuarios.findByNummedidor", query = "SELECT u FROM Usuarios u WHERE u.nummedidor = :nummedidor"),
    @NamedQuery(name = "Usuarios.findByCreatedAt", query = "SELECT u FROM Usuarios u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Usuarios.findByUpdatedAt", query = "SELECT u FROM Usuarios u WHERE u.updatedAt = :updatedAt")})
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
    @Column(name = "SEGUNDOAPELLIDO")
    private String segundoapellido;
    @Column(name = "APADOSN")
    private String apadosn;
    @Lob
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CELULAR")
    private String celular;
    @Lob
    @Column(name = "SECTOR")
    private String sector;
    @Lob
    @Column(name = "REFERENCIA")
    private String referencia;
    @Lob
    @Column(name = "FOTO")
    private byte[] foto;
    @Lob
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "VISTO")
    private String visto;
    @Column(name = "ESTADO_MEDIDOR")
    private String estadoMedidor;
    @Column(name = "NUMMEDIDOR")
    private String nummedidor;
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinTable(name = "usuarios_medidor", joinColumns = {
        @JoinColumn(name = "Usuarios_IDUSUARIO", referencedColumnName = "IDUSUARIO")}, inverseJoinColumns = {
        @JoinColumn(name = "medidorList_IDMEDIDOR", referencedColumnName = "IDMEDIDOR")})
    @ManyToMany
    private Collection<Medidor> medidorCollection;
    @OneToMany(mappedBy = "idusuario")
    private Collection<Aguasobrante> aguasobranteCollection;
    @JoinColumn(name = "IDINSTITUCION", referencedColumnName = "IDINSTITUCION")
    @ManyToOne(optional = false)
    private Institucion idinstitucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private Collection<Medidor> medidorCollection1;
    @OneToMany(mappedBy = "idusuario")
    private Collection<Aguaganaderia> aguaganaderiaCollection;

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

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVisto() {
        return visto;
    }

    public void setVisto(String visto) {
        this.visto = visto;
    }

    public String getEstadoMedidor() {
        return estadoMedidor;
    }

    public void setEstadoMedidor(String estadoMedidor) {
        this.estadoMedidor = estadoMedidor;
    }

    public String getNummedidor() {
        return nummedidor;
    }

    public void setNummedidor(String nummedidor) {
        this.nummedidor = nummedidor;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public Collection<Medidor> getMedidorCollection() {
        return medidorCollection;
    }

    public void setMedidorCollection(Collection<Medidor> medidorCollection) {
        this.medidorCollection = medidorCollection;
    }

    @XmlTransient
    public Collection<Aguasobrante> getAguasobranteCollection() {
        return aguasobranteCollection;
    }

    public void setAguasobranteCollection(Collection<Aguasobrante> aguasobranteCollection) {
        this.aguasobranteCollection = aguasobranteCollection;
    }

    public Institucion getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(Institucion idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    @XmlTransient
    public Collection<Medidor> getMedidorCollection1() {
        return medidorCollection1;
    }

    public void setMedidorCollection1(Collection<Medidor> medidorCollection1) {
        this.medidorCollection1 = medidorCollection1;
    }

    @XmlTransient
    public Collection<Aguaganaderia> getAguaganaderiaCollection() {
        return aguaganaderiaCollection;
    }

    public void setAguaganaderiaCollection(Collection<Aguaganaderia> aguaganaderiaCollection) {
        this.aguaganaderiaCollection = aguaganaderiaCollection;
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
    
     public String elimiEspacio(String m) {
        String a[] = m.split(" ");
        String s = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] != " ") {
                s += a[i];
            }
        }
        return s;
    }

    public boolean buscarUsuarios(String inicio) {
        if (inicio.isEmpty() || inicio.length() > (primerapellido.length() + segundoapellido.length() + primernombre.length() + segundonombre.length())) {
            return false;
        }
        for (int i = 0; i < inicio.length(); ++i) {
            String r = elimiEspacio(primerapellido + segundoapellido + primernombre + segundonombre);
            if (inicio.charAt(i) != (r.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean buscarCedula(String inicio) {
        if (inicio.isEmpty() || inicio.length() > (rucci.length() + 2)) {
            return false;
        }
        for (int i = 0; i < inicio.length(); ++i) {
            String r = elimiEspacio(rucci);
            if (inicio.charAt(i) != (r.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
