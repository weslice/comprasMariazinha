package controllers;

import dao.ListaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.Lista;
import model.Produtos;

/**
 * @author Wes
 * Classe Resource (Utilizei o nome controller, não confundir com o Controller Java)
 * Classe que vai conter os dados de acesso aos metodos REST do webservice
 */
@Path("lista") //Path define o URL do recurso
public class ListaController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/carregarlista")//caminho        
    //@Produces("application/json")
    public List<Lista> carregarListas() throws SQLException { //Retorna o "Bean"/Model
        return ListaDAO.getInstance().carregarListas();
    }

    /**
     *
     * @param nomeLista
     * @return
     * @throws SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{nomeLista}")
    public Lista buscarLista(@PathParam("nomeLista") String nomeLista) throws SQLException {
        return ListaDAO.getInstance().buscarLista(nomeLista);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codLista}/")
    public Lista buscarListaByID(@PathParam("codLista") int codLista) throws SQLException {
        return ListaDAO.getInstance().buscarListaByID(codLista);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarProdutoLista/{codLista}/")
    public ArrayList<Produtos> trazerProdutosPorListas(@PathParam("codLista") int codLista) throws SQLException {
        return ListaDAO.getInstance().trazerProdutosPorListas(codLista);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) //Consumes - Recebe
    @Path("/inserirProdutoLista/{quantidade}/{situacao}")
    public Response inserirProdutoNaLista(Produtos produto, Lista lista,
            @PathParam("quantidade") int quantidade,
            @PathParam("situacao") int situacao
            ) throws SQLException {
        //Produtos produto, Lista lista, int quantidade, int situacao
        return ListaDAO.getInstance().inserirProdutoNaLista(produto, lista, quantidade, situacao);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastrar")
    public Response inserirNovaLista(Lista lista) throws SQLException{
        return ListaDAO.getInstance().inserirNovaLista(lista);
    }
    
    
    @PUT //Para Update
    @Consumes(MediaType.APPLICATION_JSON) //Consumes - Recebe
    @Path("/atualizar")
    public Response alterarNomeLista(Lista lista) throws SQLException {
        //Retorna um response com mensagem de sucesso ou erro
        return ListaDAO.getInstance().alterarNomeLista(lista);
    }
    
    @DELETE
    @Path("{codLista}")
    public Response deletarLista(@PathParam("codLista") int codLista) throws SQLException {
        System.out.println("Deletando");
        return ListaDAO.getInstance().deletarLista(codLista);
    }
    
    
}
