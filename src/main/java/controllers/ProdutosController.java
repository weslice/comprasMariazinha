package controllers;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Produtos;

/**
 * @author Wes
 */
@Path("produtos") //Path define o URL do recurso
public class ProdutosController {

    //Realizando a conexão com a base
    //Connection con = new Conexao().getConexao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")//caminho        
    public List<Produtos> listarProdutos() { //Retorna o "Bean"/Model
        List<Produtos> produtos = new ArrayList();
        Produtos c1 = new Produtos(1, "Cerveja", 50);
        Produtos c2 = new Produtos(2, "Miojo", 60);
        Produtos c3 = new Produtos(3, "Pizza", 70);
        Produtos c4 = new Produtos(4, "Cocacola", 80);
        produtos.add(c1);
        produtos.add(c2);
        produtos.add(c3);
        produtos.add(c4);
        return produtos;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codProduto}/")
    public Produtos getProdutos(@PathParam("codProduto") int codProduto) {
        Produtos p = new Produtos(codProduto, null, codProduto);
        return p;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON) //Consumes - Recebe
    @Path("/")
    public Response inserirProduto(Produtos produto) {
//        //Retorna um response com mensagem de sucesso ou erro
//        try {
//            String sql = "insert into produto (nomeProduto,valor"
//                    + ",imagem)"
//                    + "values (?,?,?)";
//            //Não inserir codProduto, por ser um autoincrement
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1, produto.getNomeProduto());
//            preparedStatement.setDouble(2, produto.getValor());
//            preparedStatement.setString(3, produto.getImagem());
//            preparedStatement.execute();
//
//            System.out.println(produto.toString());
//
//        } catch (SQLException ex) {
//            Logger lgr = Logger.getLogger(Conexao.class.getName());
//            lgr.log(Level.SEVERE, ex.getMessage(), ex);
//        }
        return Response.status(Response.Status.OK).build();
    }

    ;
    
    @PUT //Para Update
    @Consumes(MediaType.APPLICATION_JSON) //Consumes - Recebe
    @Path("/")
    public Response alterarProduto(Produtos produto) {
        //Retorna um response com mensagem de sucesso ou erro
        System.out.println(produto.toString());
        return Response.status(Response.Status.OK).build();
    }

    ;
    
    @DELETE
    @Path("{codProduto}")
    public Response deletarProduto(@PathParam("codProduto") int codProduto) {
        System.out.println("Deletando");
        return Response.status(Response.Status.OK).build();
    }
}
