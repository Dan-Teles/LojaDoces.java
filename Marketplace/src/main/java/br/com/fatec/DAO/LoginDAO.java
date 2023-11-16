/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;
import static br.com.fatec.persistencia.Banco.connect;
import br.com.fatec.model.CadastroCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author danie
 */
public class LoginDAO {

    //variaveis auxiliares
    private CadastroCliente cliente;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    
    
    public void insertlogin(CadastroCliente dado) throws SQLException{
        try (Connection conn = connect()) {
             
            
            String SQL = "INSERT INTO LOGIN (login, senha) "
                    + "VALUES(?, ?)";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            
            //dados a serem inseridos
            pstmt.setString(1, dado.getLogin());
            pstmt.setString(2, dado.getSenha());
            
            //executa comando
            pstmt.executeUpdate();
            
            //fecha conexão
            conn.close();
        }
    }
    
    public int verificaLogin(String login, String senha) throws SQLException {
        int rowCount = 0;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM LOGIN WHERE LOGIN = ? AND SENHA = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, login);
                pstmt.setString(2, senha);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    rowCount = rs.getInt(1);
                }
            }
        }

        return rowCount;
    }
}