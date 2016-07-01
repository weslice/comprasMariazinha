package model;

/**
 * Classe Model que ira instânciar Lista
 * Tabela No BD - lista
 * @author Wes
 */
public class Lista {
    
    private int codLista;
    private String nomeLista;
    private int codProduto; //Chave estrangeira da tabela produtos.codProduto
    private int quantidadeProd;
    private double valorTotal;
    private int situacao;

    //Construtor
    public Lista(int codLista, String nomeLista, int codProduto, int quantidadeProd, int situacao) {
        this.codLista = codLista;
        this.nomeLista = nomeLista;
        this.codProduto = codProduto;
        this.quantidadeProd = quantidadeProd;
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.codLista;
        hash = 29 * hash + this.codProduto;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lista other = (Lista) obj;
        if (this.codLista != other.codLista) {
            return false;
        }
        if (this.codProduto != other.codProduto) {
            return false;
        }
        return true;
    }

    public int getCodLista() {
        return codLista;
    }

    public void setCodLista(int codLista) {
        this.codLista = codLista;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getQuantidadeProd() {
        return quantidadeProd;
    }

    public void setQuantidadeProd(int quantidadeProd) {
        this.quantidadeProd = quantidadeProd;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Lista{" + "codLista=" + codLista + ", nomeLista=" + nomeLista + ", codProduto=" + codProduto + ", quantidadeProd=" + quantidadeProd + ", valorTotal=" + valorTotal + ", situacao=" + situacao + '}';
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
    
    
    
}
