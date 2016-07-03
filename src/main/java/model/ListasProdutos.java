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
    private String produto_CodProduto;
    private int quantidade;
    private double valorTotal;
    private int situacao;

    public ListasProdutos(int lista_CodLista, String produto_CodProduto, int quantidade, double valorTotal, int situacao) {
        this.lista_CodLista = lista_CodLista;
        this.produto_CodProduto = produto_CodProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.situacao = situacao;
    }
    

    public ListasProdutos(){
        
    }

    public int getLista_CodLista() {
        return lista_CodLista;
    }

    public void setLista_CodLista(int lista_CodLista) {
        this.lista_CodLista = lista_CodLista;
    }

    public String getProduto_CodProduto() {
        return produto_CodProduto;
    }

    public void setProduto_CodProduto(String produto_CodProduto) {
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

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
    
}
