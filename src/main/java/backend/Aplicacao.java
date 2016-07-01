package backend;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Wes
 * Responsavél por iniciar a aplicação Jersey
 * Documentação: https://docs.oracle.com/cd/E19776-01/820-4867/ghqxq/index.html
 */

//Definindo qual a entrada para o WebService
@ApplicationPath("resources")
public class Aplicacao extends ResourceConfig{
    //Criado o construtor que ira mostrar onde estão os resources da aplicação
    public Aplicacao(){
        packages("controllers");
    }
    
}
