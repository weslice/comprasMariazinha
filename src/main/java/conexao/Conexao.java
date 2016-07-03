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
    final String url = "jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=convertToNull";
    //usuario do Banco de Dados
    final String usuario = "admin";
    //senha do Usuario do Banco de Dados
    final String senha = "admin";
    //variavel onde sera armazenada a conexao
    Connection con = null;

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Conexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro de Driver: " + e.getMessage());
        }

    }

    public Connection getConexao() {
        return con;
    }

    public void closeConnection() throws SQLException {
        con.close();
    }
}
