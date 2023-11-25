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
//import java.util.ArrayList;
//import java.util.Collection;
/**
 *
 * @author danie
 */
public class CadastroClienteDAO{

    private PreparedStatement pst;
    private ResultSet rs;
    
    private CadastroCliente cliente; //meu MODEL   
    
    public boolean insereCadastro(CadastroCliente obj) throws SQLException {
        boolean inseriu;
        
        try (Connection conn = connect()) {
            
            String sql = "INSERT INTO cliente (NOME, CPF, TELEFONE, EMAIL, DATANASCIMENTO, GENERO, LOGIN, SENHA) " 
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; //a ? indica parametros

            PreparedStatement pst = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

            //associar os dados do objeto Proprietario com o comando INSERT
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCpf());
            pst.setString(3, obj.getTelefone());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getDataNascimento());
            pst.setString(6, obj.getGenero());
            pst.setString(7, obj.getLogin());
            pst.setString(8, obj.getSenha());
            
            //System.out.println("chegou aqui");
            
            int linhasAfetadas = pst.executeUpdate();
            
            //executa comando
            if(linhasAfetadas > 0){
                System.out.println("Inserção bem-sucedida!");
            
                inseriu = true;//tudo certo com a inserção 
            }else{
                inseriu = false; 
                System.out.println("Nenhuma linha foi inserida.");
            }
            
            pst.close();
            conn.close();    
            
        } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Erro ao inserir no banco de dados: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
            //fecha conexão
        return inseriu;
    }


    public boolean remove(CadastroCliente obj) throws SQLException {
        boolean removeu;
        
        try (Connection conn = connect()) {
            String sql = "DELETE FROM cliente WHERE CPF = ?"; //a ? indica parametros

            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                //associar os dados do objeto Proprietario com o comando DELETE
                pst.setString(1, obj.getCpf());
                /*pst.setString(3, obj.getTelefone());
                pst.setString(4, obj.getEmail());
                pst.setString(5, obj.getDataNascimento());
                pst.setString(6, obj.getGenero());
                pst.setString(7, obj.getLogin());
                pst.setString(8, obj.getSenha());*/

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
            String sql = "UPDATE CLIENTE SET NOME = ?, TELEFONE = ?, EMAIL = ?, DATANASCIMENTO = ?, GENERO = ?, LOGIN = ?, SENHA = ? "
                    + "WHERE CPF = ?"; //a ? indica parametros

            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

                //associar os dados do objeto Proprietario com o comando UPDATE
                System.out.println("CPF: " + obj.getCpf());
                pst.setString(1, obj.getNome());
                pst.setString(2, obj.getTelefone());
                pst.setString(3, obj.getEmail());
                pst.setString(4, obj.getDataNascimento());
                pst.setString(5, obj.getGenero());
                pst.setString(6, obj.getLogin());
                pst.setString(7, obj.getSenha());
                pst.setString(8, obj.getCpf());

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
    
    public boolean contaExiste(CadastroCliente dado) throws SQLException {
        boolean existe = false;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM cliente WHERE login = ?";
            try (PreparedStatement pst = conn.prepareStatement(SQL)) {
                pst.setString(1, dado.getLogin());
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        int rowCount = rs.getInt(1);
                        System.out.println("Número de linhas encontradas: " + rowCount);
                        existe = rowCount > 0;
                    }
                }
            }
        }

        return existe;
    }

    /**
     * Busca um dado de proprietario baseado em sua Chave Primaria
     * @param obj
     * @return
     * @throws SQLException 
     */
    
    public CadastroCliente buscaID(String cpf) throws SQLException {
        
        try (Connection conn = connect()) {
            String sql = "SELECT NOME, TELEFONE, EMAIL, DATANASCIMENTO, GENERO, LOGIN, SENHA FROM cliente "
                    + "WHERE CPF = ?"; //a ? indica parametros

            PreparedStatement pst = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

            //associar os dados do objeto Proprietario com o comando UPDATE
            pst.setString(1, cpf);

            //executar o comando
            try (ResultSet rs = pst.executeQuery()) { //esse método serve para SELECT

                if(rs.next()) {
                    //mover os dados(campos da tab) do resultSet para o objeto proprietário
                    cliente = new CadastroCliente();
                    
                    cliente.setCpf(cpf);
                    cliente.setNome(rs.getString("NOME"));
                    cliente.setTelefone(rs.getString("TELEFONE"));
                    cliente.setEmail(rs.getString("EMAIL"));
                    cliente.setDataNascimento(rs.getString("DATANASCIMENTO"));
                    cliente.setGenero(rs.getString("GENERO"));
                    cliente.setLogin(rs.getString("LOGIN"));
                    cliente.setSenha(rs.getString("SENHA"));
                }
                pst.close();
                conn.close();
            }catch (SQLException ex) {
                System.out.println("Erro ao executar executar query: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
            }
        } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Erro ao conectar query no banco de dados: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
        return cliente;  
    }

/**
    @Override
    public Collection<CadastroCliente> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<CadastroCliente> lista = new ArrayList<>();
        
        try (Connection conn = connect()) {
            String sql = "SELECT * FROM CadastroCliente ";
            
            PreparedStatement pst = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            //precisa fazer filtro para listagem
            if(criterio != null && criterio.length() > 0) {
                sql += " WHERE " + criterio;
            }


            //executar o comando
            rs = pst.executeQuery(); //esse método serve para SELECT

            //Varre todo o resultado da consulta e coloca cada registro dentro
            //de um objeto e coloca o objeto dentro da coleção
            while(rs.next()) {
                //criar o objeto
                cliente = new CadastroCliente();

                //mover os dados do resultSet para o objeto proprietário
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                //cliente.setDataNascimento(rs.getString("dataNascimento"));
                //cliente.setGenero(rs.getString("genero"));
                cliente.setLogin(rs.getString("login"));
                cliente.setSenha(rs.getString("senha"));

                //move o objeto para a coleção
                lista.add(cliente);
            }
            //fecha conexão
                conn.close();
        }
        
        //devolve o objeto proprietario
        return lista;
        
    }*/
}
