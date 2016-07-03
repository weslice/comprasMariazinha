/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import model.Lista;
import model.Produtos;

/**
 * Classe responsável por conter os metodos do CRUD
 *
 * @author Wes
 */
public class ListaDAO extends Conexao {

    private static ListaDAO instance;

    public static ListaDAO getInstance() {
        if (instance == null) {
            instance = new ListaDAO();
        }
        return instance;
    }

    /**
     * @autor Wes Método para Listar todos as Listas registrados
     * @return ArrayList
     */
    public ArrayList<Lista> carregarListas() throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        ResultSet rs = null;
        ArrayList<Lista> listaA = new ArrayList();
        try {
            String sql = "select codLista, nomeLista from lista";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //public Lista(int codLista, String nomeLista) {
                Lista lista = new Lista(rs.getInt("codLista"), rs.getString("nomeLista"));
                listaA.add(lista);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return listaA; //Retorna um Objeto!!
    }

    /**
     * @autor Wes Método para buscar uma lista por Nome
     * @return Produto
     */
    public Lista buscarLista(String nomeLista) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        ResultSet rs = null;
        Lista lista = new Lista();
        try {
            if (!"".equals(nomeLista)) {
                String sql = "select codLista,nomeLista where nomeLista like = %?%";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, nomeLista);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    lista.setCodLista(rs.getInt("codLista"));
                    lista.setNomeLista(rs.getString("nomeLista"));
                }
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return lista;
    }

    /**
     * @autor Wes Método para buscar lista por ID codigo
     * @return Produto
     */
    public Lista buscarListaByID(int codLista) throws SQLException {
         //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        ResultSet rs = null;
        Lista lista = new Lista();
        try {
            if (codLista > 0) {
                String sql = "select codLista,nomeLista where codLista = ? ";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, codLista);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    lista.setCodLista(rs.getInt("codLista"));
                    lista.setNomeLista(rs.getString("nomeLista"));
                }
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }

        return lista;
    }

    public ArrayList<Produtos> trazerProdutosPorListas (int codLista) throws SQLException{
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        ResultSet rs = null;
        ArrayList<Produtos> produtos = new ArrayList();
        try {
            String sql = "SELECT a.nomeProduto, a.valor,a.codProduto, b.codLista, "
                    + " c.quantidadeProd, c.valorTotal, c.situacao "
                    + " FROM lista b, produto a, ListasProdutos c"
                    + " WHERE c.Lista_codLista = b.codLista "
                    + " AND  c.Produto_codProduto = a.codProduto"
                    + " AND  c.Lista_codLista = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, codLista);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Produtos produto = new Produtos(rs.getInt("codProduto"), rs.getString("nomeProduto"),
                        rs.getDouble("valor"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return produtos; //Retorna um Objeto!!
    }
    
    /**
     * @autor Wes Método para cadastrar um produto na lista
     * @param produto
     */
    public Response inserirProdutoNaLista(Produtos produto, Lista lista, int quantidade, int situacao) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();

        double valorTotal = produto.getValor() * quantidade;
        try {
            String sql = "insert into ListasProdutos(Lista_CodLista,Produto_CodProduto,quantidade,valorTotal,situacao)"
                    + "values (?,?,?,?,?)";
            //Não inserir codProduto, por ser um autoincrement
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, lista.getCodLista());
            preparedStatement.setInt(2, produto.getCodProduto());
            preparedStatement.setInt(3, quantidade);
            preparedStatement.setDouble(4, valorTotal);
            preparedStatement.setDouble(5, situacao);
            
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return Response.status(Response.Status.OK).build();
    }
    /**
     * @autor Wes Método para cadastrar uma lista
     * @param produto
     */
    public Response inserirNovaLista(Lista lista) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();

        try {
            String sql = "insert into lista (nomeLista)"
                    + "values (?)";
            //Não inserir codProduto, por ser um autoincrement
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, lista.getNomeLista());
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return Response.status(Response.Status.OK).build();
    }

    public Response deletarProdutoDaLista(int codProduto, int codLista) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        try {
            if ((codProduto > 0) 
                    && (codLista > 0)) {
                String sql = "delete from ListasProdutos where produto_CodProduto = 2 and Lista_codLista = 1 ";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, codProduto);
                preparedStatement.setInt(2, codLista);
                preparedStatement.execute();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return Response.status(Response.Status.OK).build();
    }

    public Response alterarNomeLista(Lista lista) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();

        try {
            if (!"".equals(lista.getNomeLista())) {
                String sql = "update lista set nomeLista= ? , where codLista = ? ";

                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, lista.getNomeLista());
                preparedStatement.setInt(2, lista.getCodLista());
                preparedStatement.execute();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return Response.status(Response.Status.OK).build();
    }
    
    public Response deletarLista(int codLista) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        try {
            if (codLista > 0) {
                String sql = "delete from lista where codLista= ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, codLista);
                preparedStatement.execute();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return Response.status(Response.Status.OK).build();
    }

}
