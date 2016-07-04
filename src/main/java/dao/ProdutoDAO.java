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
import model.Produtos;

/**
 * Classe responsável por conter os metodos do CRUD
 *
 * @author Wes
 */
public class ProdutoDAO extends Conexao {

    private static ProdutoDAO instance;

    public static ProdutoDAO getInstance() {
        if (instance == null) {
            instance = new ProdutoDAO();
        }
        return instance;
    }

    /**
     * @autor Wes Método para Listar todos os Produtos registrados
     * @return ArrayList
     */
    public ArrayList<Produtos> listarProdutos() throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        ResultSet rs = null;
        ArrayList<Produtos> produtos = new ArrayList();
        try {
            String sql = "select * from produto";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //public Produtos(int codProduto, String nomeProduto, double valor) {
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
    
    public ArrayList<Produtos> listarProdutosLista(int codLista) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        ResultSet rs = null;
        ArrayList<Produtos> produtos = new ArrayList();
        try {
            String sql = "select a.codProduto, a.nomeProduto, a.valor, a.imagem "
                    + " FROM produto a "
                    + " WHERE a.codProduto not in(select Produto_codProduto from ListasProdutos where Lista_codLista = ?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, codLista);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //public Produtos(int codProduto, String nomeProduto, double valor) {
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
     * @autor Wes Método para buscar produto por nome
     * @return Produto
     */
    public Produtos buscarProdutos(String nomeProduto) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        ResultSet rs = null;
        Produtos produto = new Produtos();
        try {
            if (!"".equals(nomeProduto)) {
                String sql = "select codProduto,nomeProduto,valor,imagem from produto where nomeProduto like = %?%";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, nomeProduto);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    produto.setCodProduto(rs.getInt("codProduto"));
                    produto.setNomeProduto(rs.getString("nomeProduto"));
                    produto.setImagem(rs.getString("imagem"));
                    produto.setValor(rs.getDouble("valor"));
                }
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return produto;
    }

    /**
     * @autor Wes Método para buscar produto por ID codigo
     * @return Produto
     */
    public Produtos buscarProdutosByID(int codProduto) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        ResultSet rs = null;
        Produtos produto = new Produtos();
        try {
            if (codProduto > 0) {
                String sql = "select codProduto,nomeProduto,valor,imagem from produto where codProduto = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, codProduto);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    produto.setCodProduto(rs.getInt("codProduto"));
                    produto.setNomeProduto(rs.getString("nomeProduto"));
                    produto.setImagem(rs.getString("imagem"));
                    produto.setValor(rs.getDouble("valor"));
                }
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }

        return produto;
    }

    /**
     * @autor Wes Método para cadastrar um produto no banco
     * @param produto
     */
    public Response inserirProduto(Produtos produto) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();

        try {
            String sql = "insert into produto (nomeProduto,valor"
                    + ",imagem)"
                    + "values (?,?,?)";
            //Não inserir codProduto, por ser um autoincrement
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, produto.getNomeProduto());
            preparedStatement.setDouble(2, produto.getValor());
            preparedStatement.setString(3, produto.getImagem());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Conexao.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            con.close();
        }
        return Response.status(Response.Status.OK).build();
    }

    public Response deletarProduto(int codProduto) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();
        try {
            if (codProduto > 0) {
                String sql = "delete from produto where codProduto= ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, codProduto);
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

    public Response alterarProduto(Produtos produto) throws SQLException {
        //Realizando a conexão com a base
        Connection con = new Conexao().getConexao();

        try {
            if (produto != null) {
                String sql = "update produto set nomeProduto = ? ,valor = ? ,imagem = ?"
                        + " where codProduto = ?";

                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, produto.getNomeProduto());
                preparedStatement.setDouble(2, produto.getValor());
                preparedStatement.setString(3, produto.getImagem());
                preparedStatement.setInt(4, produto.getCodProduto());
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
}
