/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wes
 */
@Entity
@Table(name = "lista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lista.findAll", query = "SELECT l FROM Lista l"),
    @NamedQuery(name = "Lista.findByCodLista", query = "SELECT l FROM Lista l WHERE l.codLista = :codLista"),
    @NamedQuery(name = "Lista.findByNomeLista", query = "SELECT l FROM Lista l WHERE l.nomeLista = :nomeLista"),
    @NamedQuery(name = "Lista.findByQuantidadeProd", query = "SELECT l FROM Lista l WHERE l.quantidadeProd = :quantidadeProd"),
    @NamedQuery(name = "Lista.findByValorTotal", query = "SELECT l FROM Lista l WHERE l.valorTotal = :valorTotal"),
    @NamedQuery(name = "Lista.findBySituacao", query = "SELECT l FROM Lista l WHERE l.situacao = :situacao")})
public class Lista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codLista")
    private Integer codLista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomeLista")
    private String nomeLista;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidadeProd")
    private int quantidadeProd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorTotal")
    private BigDecimal valorTotal;
    @Column(name = "situacao")
    private Integer situacao;
    @JoinColumn(name = "codProduto", referencedColumnName = "codProduto")
    @ManyToOne(optional = false)
    private Produto codProduto;

    public Lista() {
    }

    public Lista(Integer codLista) {
        this.codLista = codLista;
    }

    public Lista(Integer codLista, String nomeLista, int quantidadeProd) {
        this.codLista = codLista;
        this.nomeLista = nomeLista;
        this.quantidadeProd = quantidadeProd;
    }

    public Integer getCodLista() {
        return codLista;
    }

    public void setCodLista(Integer codLista) {
        this.codLista = codLista;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public int getQuantidadeProd() {
        return quantidadeProd;
    }

    public void setQuantidadeProd(int quantidadeProd) {
        this.quantidadeProd = quantidadeProd;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Produto getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto) {
        this.codProduto = codProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codLista != null ? codLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.codLista == null && other.codLista != null) || (this.codLista != null && !this.codLista.equals(other.codLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Lista[ codLista=" + codLista + " ]";
    }
    
}
