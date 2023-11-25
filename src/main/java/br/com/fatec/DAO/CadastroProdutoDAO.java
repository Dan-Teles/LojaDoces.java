/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.model.CadastroFornecedor;
import br.com.fatec.model.CadastroProduto;
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
public class CadastroProdutoDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    
    private CadastroProduto produto;
    
    public boolean insereProduto(CadastroProduto obj) throws SQLException {
        boolean inseriu;
        
        try (Connection conn = connect()) {
            
            String sql = "INSERT INTO PRODUTO (CODIGO, CATEGORIA, DESCRICAO, ESTOQUE, PRECO, NOMEFANTASIA) " 
                    + " VALUES(?, ?, ?, ?, ?, ?)"; //a ? indica parametros

            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

                //associar os dados do objeto Proprietario com o comando INSERT
                pst.setString(1, obj.getCodigo());
                pst.setString(2, obj.getCategoria());
                pst.setString(3, obj.getDescricao());
                pst.setFloat(4, obj.getEstoque());
                pst.setFloat(5, obj.getPreco());
                pst.setString(6, obj.getNomeFantasia());

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
    
    public CadastroProduto buscaID(String codigo) throws SQLException {
        //fornecedor = null;
        
        try (Connection conn = connect()) {
            //Comando SELECT
            String sqlQuery = "SELECT CATEGORIA, DESCRICAO, ESTOQUE, PRECO, NOMEFANTASIA FROM produto WHERE CODIGO = ?";

            PreparedStatement pst = conn.prepareStatement(sqlQuery);
                //troca a ?
            pst.setString(1, codigo);

                //Executa o comando SELECT
            try (ResultSet rs = pst.executeQuery()) {
                
                    if(rs.next()) { //achou 1 registro
                        //cria o objeto proprietario
                        produto = new CadastroProduto();
                        System.out.println("CODIGO: " + codigo);
                        //move os dados do resultSet para o objeto proprietario

                        produto.setCodigo(codigo);
                        produto.setCategoria(rs.getString("CATEGORIA"));
                        produto.setDescricao(rs.getString("DESCRICAO"));
                        produto.setEstoque(rs.getFloat("ESTOQUE"));
                        produto.setPreco(rs.getFloat("PRECO"));
                        produto.setNomeFantasia(rs.getString("NOMEFANTASIA"));

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
        
        return produto;
    }
    
    public boolean altera(CadastroProduto obj) throws SQLException {
        boolean alterou;
        
        try (Connection conn = connect()) {
            String sql = "UPDATE produto SET CATEGORIA = ?, DESCRICAO = ?, ESTOQUE = ?, PRECO = ?, NomeFantasia = ? "
                    + "WHERE CODIGO = ?"; //a ? indica parametros
                
            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                //fornecedor.setCnpj(obj.getCnpj());
                //associar os dados do objeto Proprietario com o comando UPDATE
                System.out.println("CODIGO: " + obj.getCodigo());
                pst.setString(1, obj.getCategoria());
                pst.setString(2, obj.getDescricao());
                pst.setFloat(3, obj.getEstoque());
                pst.setFloat(4, obj.getPreco());
                pst.setString(5, obj.getNomeFantasia());
                pst.setString(6, obj.getCodigo());
                
                //executar o comando
                int linhasAfetadas = pst.executeUpdate(); //esse método serve para Insert, delete e update
                
                if(linhasAfetadas > 0){
                    //mensagem.info("Alteração bem-sucedida!");
                    System.out.println("Alteração bem-sucedida!");
                    alterou = true;//tudo certo com a inserção 
                }else{
                    //mensagem.alert("Nenhuma linha foi alterada.");
                    alterou = false; 
                    System.out.println("Nenhuma linha foi alterada.");
                }

                //fecha a conexao
                pst.close();
                conn.close();

                return alterou;
            } catch (SQLException ex) {
            // Trate a exceção de maneira específica
                System.out.println("Erro ao carregar query para alterar registro: " + ex.getMessage());
                throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
            }
        } catch (SQLException ex) {
            // Trate a exceção de maneira específica
                System.out.println("Erro ao Conectar com banco durante a alteração: " + ex.getMessage());
                throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }         
    }
    
    public boolean remove(CadastroProduto obj) throws SQLException {
        boolean deletou;
        
        try (Connection conn = connect()) {
            String sql = "DELETE FROM produto WHERE CODIGO = ?"; //a ? indica parametros

            
            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                //associar os dados do objeto Proprietario com o comando DELETE
                pst.setString(1, obj.getCodigo());

                //executar o comando
                int linhasAfetadas = pst.executeUpdate(); //esse método serve para Insert, delete e update

                if(linhasAfetadas > 0){
                    //mensagem.info("Fornecedor Excluido com sucesso!");
                    System.out.println("Produto Excluido com sucesso!");
                    deletou = true;//tudo certo com a inserção 
                }else{
                    deletou = false; 
                    //mensagem.alert("Nenhuma linha foi excluida.");
                    System.out.println("Nenhuma linha foi excluida.");
                }

                //fecha a conexao
                pst.close();
                conn.close();

                //devolve se funcionoou ou nao
                return deletou;
            }
        } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Erro ao conectar registro de exclusão: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
    }
    
    public boolean contaExiste(CadastroProduto dado) throws SQLException {
        boolean existe = false;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM PRODUTO WHERE CODIGO = ?";
            try (PreparedStatement pst = conn.prepareStatement(SQL)) {
                pst.setString(1, dado.getCodigo());
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        int rowCount = rs.getInt(1);
                        System.out.println("Número de linhas encontradas: " + rowCount);
                        existe = rowCount > 0;
                    }
                }
            }
        } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Conta já cadastrada!");
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }

        return existe;
    }
    
}
