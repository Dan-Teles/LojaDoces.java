/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;
import static br.com.fatec.persistencia.Banco.connect;
import br.com.fatec.model.CadastroCliente;
import br.com.fatec.model.CadastroProduto;
import br.com.fatec.model.Login;
import static br.com.fatec.persistencia.Banco.connect;
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
    
    private Login login;
    
    
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
    
    public boolean remove(CadastroCliente obj) throws SQLException {
        boolean removeu;
        
        try (Connection conn = connect()) {
            String sql = "DELETE FROM login WHERE LOGIN = ?"; //a ? indica parametros

            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                //associar os dados do objeto Proprietario com o comando DELETE
                pst.setString(1, obj.getLogin());
                
                if(pst.executeUpdate() > 0)
                    removeu = true; //tudo certo com a inserção
                else
                    removeu = false; 

                    //fecha conexão
                pst.close();
                conn.close();
                }
        }catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Erro ao conectar registro de exclusão: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
        return removeu;
    }
    
    public boolean altera(CadastroCliente obj) throws SQLException {
        boolean alterou;
        
        try (Connection conn = connect()) {
            String sql = "UPDATE login SET senha = ? "
                    + "WHERE login = ?"; //a ? indica parametros

            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

                pst.setString(2, obj.getLogin());
                pst.setString(1, obj.getSenha());

                if(pst.executeUpdate() > 0)
                        alterou = true; //tudo certo com a inserção
                    else
                        alterou = false; 

                    //fecha conexão
                    conn.close();
                    return alterou;
            }catch (SQLException ex) {
                // Trate a exceção de maneira específica
                    System.out.println("Erro ao carregar query para alterar registro: " + ex.getMessage());
                    throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
            }
        }catch (SQLException ex) {
            // Trate a exceção de maneira específica
                System.out.println("Erro ao Conectar com banco durante a alteração: " + ex.getMessage());
                throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
    }
    
    public Login buscaID(String log) throws SQLException {
        //fornecedor = null;
        
        try (Connection conn = connect()) {
            //Comando SELECT
            String sqlQuery = "SELECT SENHA FROM login WHERE LOGIN = ?";

            PreparedStatement pst = conn.prepareStatement(sqlQuery);
                //troca a ?
            pst.setString(1, log);

                //Executa o comando SELECT
            try (ResultSet rs = pst.executeQuery()) {
                
                    if(rs.next()) { //achou 1 registro
                        //cria o objeto proprietario
                        login = new Login();
                        System.out.println("Login: " + log);
                        //move os dados do resultSet para o objeto proprietario

                        login.setLogin(log);
                        login.setSenha(rs.getString("SENHA"));

                    pst.close();
                    conn.close();
                    }
                } catch (SQLException ex) {
                    //mensagem.alert("Erro ao buscar CNPJ");
                    System.out.println("Erro ao executar executar query: " + ex.getMessage());
                throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
                }
        } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Erro ao conectar query no banco de dados: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
        
        return login;
    }
}