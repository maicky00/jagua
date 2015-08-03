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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "detallefactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallefactura.findAll", query = "SELECT d FROM Detallefactura d"),
    @NamedQuery(name = "Detallefactura.findByIddetallefac", query = "SELECT d FROM Detallefactura d WHERE d.iddetallefac = :iddetallefac"),
    @NamedQuery(name = "Detallefactura.findByMedidaant", query = "SELECT d FROM Detallefactura d WHERE d.medidaant = :medidaant"),
    @NamedQuery(name = "Detallefactura.findByMedidaact", query = "SELECT d FROM Detallefactura d WHERE d.medidaact = :medidaact"),
    @NamedQuery(name = "Detallefactura.findByConsumo", query = "SELECT d FROM Detallefactura d WHERE d.consumo = :consumo"),
    @NamedQuery(name = "Detallefactura.findByMedexcedido", query = "SELECT d FROM Detallefactura d WHERE d.medexcedido = :medexcedido"),
    @NamedQuery(name = "Detallefactura.findByTarexcedido", query = "SELECT d FROM Detallefactura d WHERE d.tarexcedido = :tarexcedido"),
    @NamedQuery(name = "Detallefactura.findBySubtotal", query = "SELECT d FROM Detallefactura d WHERE d.subtotal = :subtotal"),
    @NamedQuery(name = "Detallefactura.findByTotal", query = "SELECT d FROM Detallefactura d WHERE d.total = :total")})
public class Detallefactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDETALLEFAC")
    private Integer iddetallefac;
    @Column(name = "MEDIDAANT")
    private Integer medidaant;
    @Column(name = "MEDIDAACT")
    private Integer medidaact;
    @Column(name = "CONSUMO")
    private Integer consumo;
    @Column(name = "MEDEXCEDIDO")
    private Integer medexcedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TAREXCEDIDO")
    private Float tarexcedido;
    @Column(name = "SUBTOTAL")
    private Float subtotal;
    @Column(name = "TOTAL")
    private Float total;
    @JoinColumn(name = "IDMEDIDOR", referencedColumnName = "IDMEDIDOR")
    @ManyToOne
    private Medidor idmedidor;
    @JoinColumn(name = "IDPAGOMINGA", referencedColumnName = "IDPAGOMINGA")
    @ManyToOne
    private Pagosmingas idpagominga;
    @JoinColumn(name = "IDTARIFAS", referencedColumnName = "IDTARIFAS")
    @ManyToOne
    private Tarifas idtarifas;
    @JoinColumn(name = "IDOTPAGOS", referencedColumnName = "IDOTPAGOS")
    @ManyToOne
    private Otrospagos idotpagos;
    @OneToMany(mappedBy = "iddetallefac")
    private List<Facturas> facturasList;

    public Detallefactura() {
    }

    public Detallefactura(Integer iddetallefac) {
        this.iddetallefac = iddetallefac;
    }

    public Integer getIddetallefac() {
        return iddetallefac;
    }

    public void setIddetallefac(Integer iddetallefac) {
        this.iddetallefac = iddetallefac;
    }

    public Integer getMedidaant() {
        return medidaant;
    }

    public void setMedidaant(Integer medidaant) {
        this.medidaant = medidaant;
    }

    public Integer getMedidaact() {
        return medidaact;
    }

    public void setMedidaact(Integer medidaact) {
        this.medidaact = medidaact;
    }

    public Integer getConsumo() {
        return consumo;
    }

    public void setConsumo(Integer consumo) {
        this.consumo = consumo;
    }

    public Integer getMedexcedido() {
        return medexcedido;
    }

    public void setMedexcedido(Integer medexcedido) {
        this.medexcedido = medexcedido;
    }

    public Float getTarexcedido() {
        return tarexcedido;
    }

    public void setTarexcedido(Float tarexcedido) {
        this.tarexcedido = tarexcedido;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Medidor getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(Medidor idmedidor) {
        this.idmedidor = idmedidor;
    }

    public Pagosmingas getIdpagominga() {
        return idpagominga;
    }

    public void setIdpagominga(Pagosmingas idpagominga) {
        this.idpagominga = idpagominga;
    }

    public Tarifas getIdtarifas() {
        return idtarifas;
    }

    public void setIdtarifas(Tarifas idtarifas) {
        this.idtarifas = idtarifas;
    }

    public Otrospagos getIdotpagos() {
        return idotpagos;
    }

    public void setIdotpagos(Otrospagos idotpagos) {
        this.idotpagos = idotpagos;
    }

    @XmlTransient
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallefac != null ? iddetallefac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactura)) {
            return false;
        }
        Detallefactura other = (Detallefactura) object;
        if ((this.iddetallefac == null && other.iddetallefac != null) || (this.iddetallefac != null && !this.iddetallefac.equals(other.iddetallefac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallefactura[ iddetallefac=" + iddetallefac + " ]";
    }
    
}
