/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.CadastroProdutoDAO;
import br.com.fatec.model.CadastroProduto;
import static br.com.fatec.persistencia.Banco.connect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class CadastroProdutoController implements Initializable {

    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnDeletar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtDescricao;
    @FXML
    private ComboBox<String> cboxCategoria;
    @FXML
    private TextField txtEstoque;
    @FXML
    private TextField txtPreco;
    @FXML
    private Button btnExibir;
    
    CadastroProduto produto = new CadastroProduto();
    
    @FXML
    private ComboBox<String> cboxFornecedor;
    @FXML
    private Button btnProdutos;
    @FXML
    private Button btnVoltar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboxCategoria.getItems().addAll("CHOCOLATE", "BOLO", "BISCOITO", "TRUFA");  
        cboxFornecedor.getItems().clear();
        
        try (Connection conn = connect()) {
            String sql = "SELECT NomeFantasia FROM FORNECEDOR";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Executa a consulta
                try (ResultSet resultado = stmt.executeQuery()) {
                    // Adiciona os CNPJs ao ComboBox
                    while (resultado.next()) {
                        String nomeFantasia = resultado.getString("NomeFantasia");
                        cboxFornecedor.getItems().add(nomeFantasia);
                    }
                }
            }catch(SQLException e){
                System.out.println("Erro ao carregar fornecedor");
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        // TODO
    }    
    
    
    @FXML
    private void btnCadastrar_Cliente_Click(ActionEvent event) throws SQLException {
        if(!todosCamposPreenchidos()){
            return;
        }
        
        produto = moveViewToModel();
        
        //VERIFICANDO
        CadastroProdutoDAO Cadastro = new CadastroProdutoDAO();

        boolean contaExiste = Cadastro.contaExiste(produto);
        
        if(contaExiste){
            return;
            //alert("Já existe um Fornecedor cadastrado com esse CNPJ.");
        } else{
            //GRAVANDO
            try {
                if(Cadastro.insereProduto(produto) == true){    
                    info("Fornecedor cadastrado com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao cadastrar produto!");
                }
            }catch (SQLException ex) {
                //alert("Deu erro no cadastro");
                System.out.println("Deu erro: " + 
                        ex.getMessage());
            } 
            //}
        }
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
        if(!todosCamposPreenchidos()){
            return;
        }
        
        String codigoProduto = txtCodigo.getText();

        //produto.setCodigo(codigoProduto);
        produto = moveViewToModel();
        
        CadastroProdutoDAO Cadastro = new CadastroProdutoDAO();

            try {
                if(Cadastro.altera(produto) == true){    
                    info("Fornecedor alterado com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao alterar dados do produto!");
                }
            }
            catch (SQLException ex) {
                alert("Deu erro na alteração");
                System.out.println("Deu erro na alteração: " + 
                        ex.getMessage());
            }
    }

    @FXML
    private void btnDeletar_Click(ActionEvent event) {
        if(!todosCamposPreenchidos()){
            return;
        }
        
        String codigoProduto = txtCodigo.getText();
        
        //produto.setCodigo(codigoProduto);
        produto = moveViewToModel();
        
        //CadastroFornecedor fornecedor = new CadastroFornecedor(); AQUI ESTA O ERRO
        CadastroProdutoDAO Cadastro = new CadastroProdutoDAO();
        //Cadastro.buscaID(cnpjFornecedor);
        //boolean contaExiste = Cadastro.contaExiste(cnpjFornecedor);
        
        //if(contaExiste){
            try {
                if(Cadastro.remove(produto) == true){    
                    info("Excluido com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao remover produto!");
                }
            }
            catch (SQLException ex) {
                System.out.println("Deu erro na exclusão: " + 
                        ex.getMessage());
            }
    }
    
    @FXML
    private void btnProdutos_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/produto.fxml");
    }
    
    @FXML
    private void btnExibir_Click(ActionEvent event) throws SQLException {
        String codigoProduto = txtCodigo.getText();
        
        produto = moveModelToView(codigoProduto);
        
        //fornecedor = moveModelToView(cnpjFornecedor);
    }
    
    private CadastroProduto moveViewToModel(){
        
        produto = new CadastroProduto();
        
        produto.setCodigo(txtCodigo.getText());
        produto.setCategoria(cboxCategoria.getValue());
        produto.setDescricao(txtDescricao.getText());
        produto.setEstoque(Float.parseFloat(txtEstoque.getText()));
        produto.setPreco(Float.parseFloat(txtPreco.getText()));
        produto.setNomeFantasia(cboxFornecedor.getValue());
        
        return produto;
    }
    
    private CadastroProduto moveModelToView(String codigo) throws SQLException {
        CadastroProdutoDAO ProdDAO = new CadastroProdutoDAO();
        //CadastroFornecedor Cadastro = new CadastroFornecedor();
        
        CadastroProduto Cadastro = ProdDAO.buscaID(codigo);
        
        //CadastroFornecedor CadastroEncontrado = FornDAO.buscaID(cnpj);
        
        if (Cadastro != null) {
            txtCodigo.setText(Cadastro.getCodigo());
            cboxCategoria.setValue(Cadastro.getCategoria());
            txtDescricao.setText(Cadastro.getDescricao());
            txtEstoque.setText(String.valueOf(Cadastro.getEstoque()));
            txtPreco.setText(String.valueOf(Cadastro.getPreco()));
            cboxFornecedor.setValue(Cadastro.getNomeFantasia());
            info("Dados encontrados!");
        } else {
            alert("Produto não encontrado.");
        } // Trate a exceção de maneira específica
        // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        return Cadastro;
    }
    
    public void info(String msg){    
        Alert alerta = new Alert (Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
               
        alerta.showAndWait(); //exibe mensagem
    }
    
    public void alert(String msg){     
        Alert alerta = new Alert (Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(msg);
               
        alerta.showAndWait(); //exibe mensagem
    }
    
    private boolean todosCamposPreenchidos() {
    // Adicione todos os campos que você deseja verificar
        if (txtCodigo.getText().isEmpty()) {
            alert("Preencha o campo Código.");
            txtCodigo.requestFocus();
            return false;
        }
        if (txtDescricao.getText().isEmpty()) {
            alert("Preencha o campo Descrição.");
            txtDescricao.requestFocus();
            return false;
        }
        if (txtEstoque.getText().isEmpty()) {
            alert("Preencha o campo Estoque.");
            txtEstoque.requestFocus();
            return false;
        }
        if (txtPreco.getText().isEmpty()) {
            alert("Preencha o campo Preço.");
            txtPreco.requestFocus();
            return false;
        }
        if (cboxCategoria.getValue().isEmpty()) {
            alert("Preencha o campo Categoria.");
            cboxCategoria.requestFocus();
            return false;
        }
        if (cboxFornecedor.getValue().isEmpty()) {
            alert("Preencha o campo Fornecedor.");
            cboxFornecedor.requestFocus();
            return false;
        }
        

        return true;
    }
    
    private void limparCampos(){
        txtCodigo.clear();
        txtDescricao.clear();
        txtEstoque.clear();
        txtPreco.clear();
        cboxCategoria.setValue("");
        cboxFornecedor.setValue("");
        txtCodigo.requestFocus();
    }
    
    @FXML
    private void btnVoltar_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/admin.fxml");
    }
    
    private void mudaTela(ActionEvent event, String caminho){
        try {
                // Carrega o FXML 'CadastroFornecedor'
                FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
                Parent root = loader.load();
                // Obtém o Stage atual
                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Fecha o Stage atual
                stageAtual.close();

                // Cria um novo Stage para o FXML 'CadastroFornecedor'
                Stage novoStage = new Stage();
                novoStage.setScene(new Scene(root));

                // Mostra o novo Stage
                novoStage.show();
            } catch (IOException e) {
                // Lidar com exceções de IO, se necessário
                e.printStackTrace();
            }
    }
}
