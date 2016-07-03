package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe Model que ira instânciar Produtos Tabela No BD - produto
 *
 * @author Wes
 */
@XmlRootElement
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

    //Construtor Vazio
    public Produtos() {
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        if (null == this.nomeProduto) {
            return "";
        } else {
            return this.nomeProduto;
        }
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
        if (null == this.imagem) {
            return "";
        } else {
            return this.imagem;
        }
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.codProduto;
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
        final Produtos other = (Produtos) obj;
        if (this.codProduto != other.codProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produtos{" + "codProduto=" + codProduto + ", nomeProduto=" + nomeProduto + ", valor=" + valor + ", imagem=" + imagem + '}';
    }
}
