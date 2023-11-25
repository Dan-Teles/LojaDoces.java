/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.CadastroProduto;
import br.com.fatec.model.Carrinho;
import br.com.fatec.persistencia.Banco;
import static br.com.fatec.persistencia.Banco.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

/**
 *
 * @author danie
 */
public class CarrinhoDAO implements DAO <Carrinho>{
    
    private java.sql.PreparedStatement pst;
    private java.sql.ResultSet rs;
    private Carrinho carrinho;

    @Override
    public boolean insere(Carrinho obj) throws SQLException {
        boolean inseriu;
        
        try (Connection conn = connect()) {
            
            String sql = "INSERT INTO carrinho (nomeCliente, produto, quantidade, valor) " 
                    + " VALUES(?, ?, ?, ?)"; //a ? indica parametros

            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

                //associar os dados do objeto Proprietario com o comando INSERT
                pst.setString(1, obj.getLogin());
                pst.setString(2, obj.getProduto());
                pst.setFloat(3, obj.getQuantidade());
                pst.setFloat(4, obj.getValor());

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
            }catch (SQLException ex) {
                System.out.println("Erro ao cadastrar no banco de dados: " + ex.getMessage());
                throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
            }    
        } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Erro ao inserir no banco de dados: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
            //fecha conexão
        return inseriu;
    }

    @Override
    public boolean remove(Carrinho model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(Carrinho model) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Carrinho buscaID(Carrinho obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Carrinho> lista(String criterio) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
