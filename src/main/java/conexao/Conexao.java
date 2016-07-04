/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe responsavel por conectar ao banco de dados
 * @author Wes
 */
public class Conexao {

    //caminho do banco de dados
    //final String url = "jdbc:mysql://localhost:3306/mydb";
    //final String url = "jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=convertToNull";
//    final String url = "jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/teste";
    //usuario do Banco de Dados
    //final String usuario = "admin";
    final String usuario = "admin2beWc7b";
    //senha do Usuario do Banco de Dados
    final String senha = "i7dML29GEjuB";
    //final String senha = "admin";
    //variavel onde sera armazenada a conexao
    Connection con = null;

    
    
    String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");

    String url = "jdbc:mysql://"+host+":"+port+"/teste";
    
    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Conexao() {
        System.out.println(url);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, "Erro de Driver: " + e.getMessage());
        }

    }

    public Connection getConexao() {
        return con;
    }

    public void closeConnection() throws SQLException {
        con.close();
    }
}
