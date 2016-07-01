package model;

/**
 * Classe Model que ira instânciar Produtos
 * Tabela No BD - produto
 * @author Wes
 */
public class Produtos {
    
    private int codProduto;
    private String nomeProduto;
    private double valor;
    private String imagem;

    //Construtor
    public Produtos(int codProduto, String nomeProduto, double valor) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.valor = valor;
    }
    
    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    
    
}
