package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe Model que ira instânciar Lista
 * Tabela No BD - lista
 * @author Wes
 */
@XmlRootElement
public class Lista {
    
    private int codLista;
    private String nomeLista;
//    private int codProduto; //Chave estrangeira da tabela produtos.codProduto
//    private int quantidadeProd;
//    private double valorTotal;
//    private int situacao;

    //Construtor
    public Lista(int codLista, String nomeLista
            //, int codProduto, int quantidadeProd, int situacao, double valorTotal
            ) {
        this.codLista = codLista;
        this.nomeLista = nomeLista;
//        this.codProduto = codProduto;
//        this.quantidadeProd = quantidadeProd;
//        this.situacao = situacao;
//        this.valorTotal = valorTotal;
    }
    
    //Construtor vazio
    public Lista(){
        
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

   
    
    
    
}
