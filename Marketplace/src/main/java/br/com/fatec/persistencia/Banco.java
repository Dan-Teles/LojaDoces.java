/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.fatec.Controller.CadastroClienteController;

/**
 *
 * @author danie
 */
public class Banco {
    private static final String url = "jdbc:mysql://localhost:3306/loja_doce";
    private static final String user = "root";
    private static final String password = "45762558r";
    
    public static Connection connect() {
        Connection conn = null;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("MYSQL conectado com sucesso!");
            //return conn;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } 

        return conn;
    }
    
    public static void insertlogin(String login, String senha) throws SQLException{
        try (Connection conn = connect()) {

            String SQL = "INSERT INTO LOGIN (login, senha) "
                    + "VALUES(?,?)";
            
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            
            //dados a serem inseridos
            pstmt.setString(1, login);
            pstmt.setString(2, senha);
            
            //executa comando
            pstmt.executeUpdate();
            conn.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir no banco de dados: " + ex.getMessage());
            throw ex; // ou lance uma exceção mais específica se desejar
        }
            
            //fecha conexão
            //conn.close();
        //}
    }
    
        public static void verificaLogin(String senha, String login) throws SQLException{
            try (Connection conn = connect()) {


                String sql = "SELECT login FROM LOGIN WHERE SENHA LIKE 'senha' "
                        + "VALUES(?,?)";

                PreparedStatement pstmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

                //executa comando
                pstmt.executeUpdate();


                //fecha conexão
                conn.close();
            } 
        }      
}