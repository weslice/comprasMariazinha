package controllers;

import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
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
 * Classe Resource (Utilizei o nome controller, não confundir com o Controller Java)
 * Classe que vai conter os dados de acesso aos metodos REST do webservice
 */
@Path("produtos") //Path define o URL do recurso
public class ProdutosController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarTodos")//caminho        
    //@Produces("application/json")
    public List<Produtos> listarProdutos() throws SQLException { //Retorna o "Bean"/Model
        return ProdutoDAO.getInstance().listarProdutos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{nomeProduto}")
    public Produtos getProdutos(@PathParam("nomeProduto") String nomeProduto) throws SQLException {
        return ProdutoDAO.getInstance().buscarProdutos(nomeProduto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codProduto}/")
    public Produtos getProdutosByID(@PathParam("codProduto") int codProduto) throws SQLException {
        return ProdutoDAO.getInstance().buscarProdutosByID(codProduto);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON) //Consumes - Recebe
    @Path("/cadastrar")
    public Response inserirProduto(Produtos produto) throws SQLException {
        return ProdutoDAO.getInstance().inserirProduto(produto);
    }
    
    @PUT //Para Update
    @Consumes(MediaType.APPLICATION_JSON) //Consumes - Recebe
    @Path("/atualizar")
    public Response alterarProduto(Produtos produto) throws SQLException {
        //Retorna um response com mensagem de sucesso ou erro
        return ProdutoDAO.getInstance().alterarProduto(produto);
    }
    
    @DELETE
    @Path("{codProduto}")
    public Response deletarProduto(@PathParam("codProduto") int codProduto) throws SQLException {
        System.out.println("Deletando");
        return ProdutoDAO.getInstance().deletarProduto(codProduto);
    }
}
