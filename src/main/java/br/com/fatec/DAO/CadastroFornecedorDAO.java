/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;


import br.com.fatec.model.CadastroFornecedor;
import static br.com.fatec.persistencia.Banco.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.fatec.Controller.CadastroFornecedorController;

/**
 *
 * @author danie
 */
public class CadastroFornecedorDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private CadastroFornecedor fornecedor;
    private CadastroFornecedorController mensagem;
    
    public boolean insereCadastro(CadastroFornecedor obj) throws SQLException {
        boolean inseriu;
        
        try (Connection conn = connect()) {
            
            String sql = "INSERT INTO FORNECEDOR (CNPJ, RazaoSocial, NomeFantasia, EMAIL, TELEFONE, CEP, ESTADO, CIDADE, BAIRRO, LOGRADOURO, NUMERO) " 
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //a ? indica parametros

            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

                //associar os dados do objeto Proprietario com o comando INSERT
                pst.setInt(1, obj.getCnpj());
                pst.setString(2, obj.getRazaoSocial());
                pst.setString(3, obj.getNomeFantasia());
                pst.setString(4, obj.getEmail());
                pst.setString(5, obj.getTelefone());
                pst.setString(6, obj.getCep());
                pst.setString(7, obj.getEstado());
                pst.setString(8, obj.getCidade());
                pst.setString(9, obj.getBairro());
                pst.setString(10, obj.getLogradouro());
                pst.setInt(11, obj.getNumero());

                //System.out.println("chegou aqui");

                int linhasAfetadas = pst.executeUpdate();

                //executa comando
                if(linhasAfetadas > 0){
                    //mensagem.info("Inserção bem-sucedida!");
                    System.out.println("Inserção bem-sucedida!");

                    inseriu = true;//tudo certo com a inserção 
                }else{
                    //mensagem.alert("Nenhuma linha foi inserida.");
                    inseriu = false; 
                    System.out.println("Nenhuma linha foi inserida.");
                }

                pst.close();
                conn.close();
                
            } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            //mensagem.alert("Erro ao cadastrar fornecedor");
            System.out.println("Erro ao cadastrar no banco de dados: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
            }
            
        } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Erro ao inserir no banco de dados: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
        return inseriu;
            //fecha conexão
    }
    
    public boolean contaExiste(CadastroFornecedor dado) throws SQLException {
        boolean existe = false;

        try (Connection conn = connect()) {
            String SQL = "SELECT COUNT(*) FROM FORNECEDOR WHERE CNPJ = ?";
            try (PreparedStatement pst = conn.prepareStatement(SQL)) {
                pst.setInt(1, dado.getCnpj());
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
    
    public CadastroFornecedor buscaID(int cnpj) throws SQLException {
        //fornecedor = null;
        
        try (Connection conn = connect()) {
            //Comando SELECT
            String sqlQuery = "SELECT RazaoSocial, NomeFantasia, EMAIL, TELEFONE, CEP, ESTADO, CIDADE, BAIRRO, LOGRADOURO, NUMERO FROM fornecedor WHERE CNPJ = ?";

            PreparedStatement pst = conn.prepareStatement(sqlQuery);
                //troca a ?
            pst.setInt(1, cnpj);

                //Executa o comando SELECT
            try (ResultSet rs = pst.executeQuery()) {

                //le o próximo regitro
                if(rs.next()) { //achou 1 registro
                    //cria o objeto proprietario
                    fornecedor = new CadastroFornecedor();
                    System.out.println("CNPJ: " + cnpj);
                    //move os dados do resultSet para o objeto proprietario
                    //erro esta aqui
                    //fornecedor.setCnpj(rs.getInt("CNPJ"));
                    fornecedor.setCnpj(cnpj);
                    fornecedor.setRazaoSocial(rs.getString("RazaoSocial"));
                    fornecedor.setNomeFantasia(rs.getString("NomeFantasia"));
                    fornecedor.setEmail(rs.getString("EMAIL"));
                    fornecedor.setTelefone(rs.getString("TELEFONE"));
                    fornecedor.setCep(rs.getString("CEP"));
                    fornecedor.setEstado(rs.getString("ESTADO"));
                    fornecedor.setCidade(rs.getString("CIDADE"));
                    fornecedor.setBairro(rs.getString("BAIRRO"));
                    fornecedor.setLogradouro(rs.getString("LOGRADOURO"));
                    fornecedor.setNumero(rs.getInt("NUMERO"));

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
        
        return fornecedor;
    }
    
    public boolean altera(CadastroFornecedor obj) throws SQLException {
        boolean alterou;
        
        try (Connection conn = connect()) {
            String sql = "UPDATE FORNECEDOR SET RazaoSocial = ?, NomeFantasia = ?, EMAIL = ?, TELEFONE = ?, CEP = ?, ESTADO = ?, CIDADE = ?, BAIRRO = ?, LOGRADOURO = ?, NUMERO = ? "
                    + "WHERE CNPJ = ?"; //a ? indica parametros
                
            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                //fornecedor.setCnpj(obj.getCnpj());
                //associar os dados do objeto Proprietario com o comando UPDATE
                System.out.println("CNPJ: " + obj.getCnpj());
                pst.setString(1, obj.getRazaoSocial());
                pst.setString(2, obj.getNomeFantasia());
                pst.setString(3, obj.getEmail());
                pst.setString(4, obj.getTelefone());
                pst.setString(5, obj.getCep());
                pst.setString(6, obj.getEstado());
                pst.setString(7, obj.getCidade());
                pst.setString(8, obj.getBairro());
                pst.setString(9, obj.getLogradouro());
                pst.setInt(10, obj.getNumero());
                pst.setInt(11, obj.getCnpj());

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
    
    public boolean remove(CadastroFornecedor obj) throws SQLException {
        boolean deletou;
        
        try (Connection conn = connect()) {
            String sql = "DELETE FROM fornecedor WHERE CNPJ = ?"; //a ? indica parametros

            
            try(PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                //associar os dados do objeto Proprietario com o comando DELETE
                pst.setInt(1, obj.getCnpj());

                //executar o comando
                int linhasAfetadas = pst.executeUpdate(); //esse método serve para Insert, delete e update

                if(linhasAfetadas > 0){
                    //mensagem.info("Fornecedor Excluido com sucesso!");
                    System.out.println("Fornecedor Excluido com sucesso!");
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
    
    /**public Collection<CadastroFuncionario> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<CadastroFuncionario> lista = new ArrayList<>();
        
        try (Connection conn = connect()) {
        
            String sql = "SELECT * FROM Proprietario ";

            //precisa fazer filtro para listagem
            if(criterio != null && criterio.length() > 0) {
                sql += " WHERE " + criterio;
            }

            //abre a conexao com o banco
            Banco.conectar();

            //preparar o comando PST
            pst = Banco.obterConexao().prepareStatement(sql);

            //executar o comando
            rs = pst.executeQuery(); //esse método serve para SELECT

            //Varre todo o resultado da consulta e coloca cada registro dentro
            //de um objeto e coloca o objeto dentro da coleção
            while(rs.next()) {
                //criar o objeto
                proprietario = new Proprietario();

                //mover os dados do resultSet para o objeto proprietário
                proprietario.setCodProprietario(rs.getInt("codProprietario"));
                proprietario.setNome(rs.getString("Nome"));

                //move o objeto para a coleção
                lista.add(proprietario);
            }

            //fecha a conexao
            Banco.desconectar();

            //devolve o objeto proprietario
            return lista;
        } catch (SQLException ex) {
        // Trate a exceção de maneira específica
            System.out.println("Erro ao alterar registro: " + ex.getMessage());
            throw ex; // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        }
    }*/
}
