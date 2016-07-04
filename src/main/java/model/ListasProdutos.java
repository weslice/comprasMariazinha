/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wes
 */
@XmlRootElement
public class ListasProdutos {

    private int lista_CodLista;
    private int produto_CodProduto;
    private int quantidade;
    private double valorTotal; //Valor Total = ValorProduto * quantidade != do totalizador
    private double valorProduto;
    private double totalizador; //totalizador = soma de todos os valores totais
    private boolean situacao;
    private String nomeProduto;

    public ListasProdutos(int lista_CodLista, int produto_CodProduto, int quantidade, double valorTotal, double valorProduto, boolean situacao,
            String nomeProduto, double totalizador) {
        this.lista_CodLista = lista_CodLista;
        this.produto_CodProduto = produto_CodProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorProduto = valorProduto;
        this.situacao = situacao;
        this.nomeProduto = nomeProduto;
        this.totalizador = totalizador;
    }

    public ListasProdutos() {
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getTotalizador() {
        return totalizador;
    }

    public void setTotalizador(double totalizador) {
        this.totalizador = totalizador;
    }
    
    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public int getLista_CodLista() {
        return lista_CodLista;
    }

    public void setLista_CodLista(int lista_CodLista) {
        this.lista_CodLista = lista_CodLista;
    }

    public int getProduto_CodProduto() {
        return produto_CodProduto;
    }

    public void setProduto_CodProduto(int produto_CodProduto) {
        this.produto_CodProduto = produto_CodProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
}
