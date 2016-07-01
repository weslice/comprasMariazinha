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

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
    
    
}
