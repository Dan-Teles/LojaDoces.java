/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.CadastroFornecedorDAO;
import br.com.fatec.model.CadastroFornecedor;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class CadastroFornecedorController implements Initializable {

    @FXML
    private TextField txtRazaoSocial;
    @FXML
    private TextField txtNomeFantasia;
    @FXML
    private TextField txtCNPJ;
    @FXML
    private PasswordField txtLogradouro;
    @FXML
    private PasswordField txtBairro;
    @FXML
    private PasswordField txtCidade;
    @FXML
    private PasswordField txtCEP;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private Button btnCadastrar;
    @FXML
    private TextField txtEstado;
    @FXML
    private PasswordField txtNumero;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnExibir;
    
    //VARIAVEL
    CadastroFornecedor fornecedor = new CadastroFornecedor();
    @FXML
    private Button btnAdmin;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnExibir_Click(ActionEvent event) throws SQLException {
        if (txtCNPJ.getText().isEmpty()) {
            alert("Preencha o campo CNPJ.");
            txtCNPJ.requestFocus();
        } else{
            int cnpjFornecedor = Integer.parseInt(txtCNPJ.getText());

            fornecedor = moveModelToView(cnpjFornecedor);
        }
    }

    @FXML
    private void btnCadastrar_Click(ActionEvent event) throws SQLException {
        if(!todosCamposPreenchidos()){
            return;
        }
        
        fornecedor = moveViewToModel();
        
        //VERIFICANDO
        CadastroFornecedorDAO Cadastro = new CadastroFornecedorDAO();

        boolean contaExiste = Cadastro.contaExiste(fornecedor);
        
        if(contaExiste){
            return;
            //alert("Já existe um Fornecedor cadastrado com esse CNPJ.");
        } else{
            //GRAVANDO
            try {
                if(Cadastro.insereCadastro(fornecedor) == true){    
                    info("Fornecedor cadastrado com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao cadastrar fornecedor!");
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
    private void btnAtualizar_Click(ActionEvent event) throws SQLException {
        if(!todosCamposPreenchidos()){
            return;
        }
        
        int cnpjFornecedor = Integer.parseInt(txtCNPJ.getText());

        fornecedor.setCnpj(cnpjFornecedor);
        fornecedor = moveViewToModel();
        
        //CadastroFornecedor fornecedor = new CadastroFornecedor(); AQUI ESTA O ERRO
        CadastroFornecedorDAO Cadastro = new CadastroFornecedorDAO();
        //Cadastro.buscaID(cnpjFornecedor);
        //boolean contaExiste = Cadastro.contaExiste(cnpjFornecedor);
        
        //if(contaExiste){
            try {
                if(Cadastro.altera(fornecedor) == true){    
                    info("Fornecedor alterado com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao alterar dados do fornecedor!");
                }
            }
            catch (SQLException ex) {
                alert("Deu erro na alteração");
                System.out.println("Deu erro na alteração: " + 
                        ex.getMessage());
            }  
        //} else{
            //GRAVANDO
            //alert("Não existe uma Fornecedor cadastrado com esse CNPJ.");
    }
        
    @FXML
    private void btnExcluir_Click(ActionEvent event) {
        if(!todosCamposPreenchidos()){
            return;
        }
        
        int cnpjFornecedor = Integer.parseInt(txtCNPJ.getText());
        
        fornecedor.setCnpj(cnpjFornecedor);
        fornecedor = moveViewToModel();
        
        //CadastroFornecedor fornecedor = new CadastroFornecedor(); AQUI ESTA O ERRO
        CadastroFornecedorDAO Cadastro = new CadastroFornecedorDAO();
        //Cadastro.buscaID(cnpjFornecedor);
        //boolean contaExiste = Cadastro.contaExiste(cnpjFornecedor);
        
        //if(contaExiste){
            try {
                if(Cadastro.remove(fornecedor) == true){    
                    info("Excluido com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao remover dados do fornecedor!");
                }
            }
            catch (SQLException ex) {
                alert("Deu erro na exclusão");
                System.out.println("Deu erro na exclusão: " + 
                        ex.getMessage());
            }
    }
    
    @FXML
    private void btnAdmin_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/admin.fxml");
    }
    
    private CadastroFornecedor moveViewToModel(){
        
        fornecedor = new CadastroFornecedor();
        fornecedor.setCnpj(Integer.parseInt(txtCNPJ.getText()));
        fornecedor.setRazaoSocial(txtRazaoSocial.getText());
        fornecedor.setNomeFantasia(txtNomeFantasia.getText());
        fornecedor.setEmail(txtEmail.getText());
        fornecedor.setTelefone(txtTelefone.getText());
        fornecedor.setCep(txtCEP.getText());
        fornecedor.setEstado(txtEstado.getText());
        fornecedor.setBairro(txtBairro.getText());
        fornecedor.setCidade(txtCidade.getText());
        fornecedor.setLogradouro(txtLogradouro.getText());
        fornecedor.setNumero(Integer.parseInt(txtNumero.getText()));
        
        return fornecedor;
    }
    
    private CadastroFornecedor moveModelToView(int cnpj) throws SQLException {
        CadastroFornecedorDAO FornDAO = new CadastroFornecedorDAO();
        //CadastroFornecedor Cadastro = new CadastroFornecedor();
        
        CadastroFornecedor Cadastro = FornDAO.buscaID(cnpj);
        
        //CadastroFornecedor CadastroEncontrado = FornDAO.buscaID(cnpj);
        
        if (Cadastro != null) {
            txtCNPJ.setText(String.valueOf(Cadastro.getCnpj()));
            txtBairro.setText(Cadastro.getBairro());
            txtCEP.setText(Cadastro.getCep());
            txtCidade.setText(Cadastro.getCidade());
            txtEmail.setText(Cadastro.getEmail());
            txtEstado.setText(Cadastro.getEstado());
            txtLogradouro.setText(Cadastro.getLogradouro());
            txtNomeFantasia.setText(Cadastro.getNomeFantasia());
            txtNumero.setText(String.valueOf(Cadastro.getNumero()));
            txtRazaoSocial.setText(Cadastro.getRazaoSocial());
            txtTelefone.setText(Cadastro.getTelefone());
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
        if (txtRazaoSocial.getText().isEmpty()) {
            alert("Preencha o campo Razao Social.");
            txtRazaoSocial.requestFocus();
            return false;
        }
        if (txtNomeFantasia.getText().isEmpty()) {
            alert("Preencha o campo Nome Fantasia.");
            txtNomeFantasia.requestFocus();
            return false;
        }
        if (txtCNPJ.getText().isEmpty()) {
            alert("Preencha o campo CNPJ.");
            txtCNPJ.requestFocus();
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            alert("Preencha o campo Email.");
            txtEmail.requestFocus();
            return false;
        }
        if (txtTelefone.getText().isEmpty()) {
            alert("Preencha o campo Telefone.");
            txtTelefone.requestFocus();
            return false;
        }
        if (txtCEP.getText().isEmpty()) {
            alert("Preencha o campo CEP.");
            txtCEP.requestFocus();
            return false;
        }
        if (txtEstado.getText().isEmpty()) {
            alert("Preencha o campo Estado.");
            txtEstado.requestFocus();
            return false;
        }
        if (txtBairro.getText().isEmpty()) {
            alert("Preencha o campo Bairro.");
            txtBairro.requestFocus();
            return false;
        }
        if (txtCidade.getText().isEmpty()) {
            alert("Preencha o campo Cidade.");
            txtCidade.requestFocus();
            return false;
        }
        if (txtLogradouro.getText().isEmpty()) {
            alert("Preencha o campo Logradouro.");
            txtLogradouro.requestFocus();
            return false;
        }
        if (txtNumero.getText().isEmpty()) {
            alert("Preencha o campo Número.");
            txtNumero.requestFocus();
            return false;
        }

        return true;
    }
    
    private void limparCampos(){
        txtRazaoSocial.clear();
        txtNomeFantasia.clear();
        txtCNPJ.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtCEP.clear();
        txtEstado.clear();
        txtBairro.clear();
        txtCidade.clear();
        txtLogradouro.clear();
        txtNumero.clear();
        txtRazaoSocial.requestFocus();
    }
    
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
