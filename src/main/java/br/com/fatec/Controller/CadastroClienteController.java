/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.CadastroClienteDAO;
import br.com.fatec.DAO.LoginDAO;
import br.com.fatec.model.CadastroCliente;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class CadastroClienteController implements Initializable {
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private CheckBox chkVisualizar;
    @FXML
    private PasswordField txtConfirmarSenha;
    @FXML
    private Button btnCadastrar;
    @FXML
    private DatePicker dtDataNascimento;
    @FXML
    private ComboBox<String> cmbGenero;
    
    //variavel
    CadastroCliente cliente = new CadastroCliente();
    @FXML
    private Pane pnCliente;
    @FXML
    private GridPane tblCliente;
    @FXML
    private Button btnExibir;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextField txtSenhaCheck;
    @FXML
    private TextField txtConfirmarCheck;
    @FXML
    private TextField txtLoginCheck;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnVoltar;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        //cb_regiao.getItems().addAll("Zona Norte","Zona Sul", "Zona Leste", "Zona Oeste", "Centro");
        cmbGenero.getItems().addAll("MASCULINO", "FEMININO", "NAO DECLARAR");
        
        txtConfirmarCheck.setVisible(false);
        txtSenhaCheck.setVisible(false);
        txtLoginCheck.setVisible(false);
        
        txtSenha.setVisible(true);
        txtLogin.setVisible(true);
        txtConfirmarSenha.setVisible(true);
        //
    }
   

    /**
     * Initializes the controller class.
     */

    
    @FXML
    private void chkVisualizar_Click(ActionEvent event) {
       if (chkVisualizar.isSelected()){
           String senha = txtSenha.getText();
           txtSenhaCheck.setText(senha);
           txtSenhaCheck.setVisible(true);
           txtSenha.setVisible(false);
           
           String login = txtLogin.getText();
           txtLoginCheck.setText(login);
           txtLoginCheck.setVisible(true);
           txtLogin.setVisible(false);
           
           String confirmarSenha = txtConfirmarSenha.getText();
           txtConfirmarCheck.setText(confirmarSenha);
           txtConfirmarCheck.setVisible(true);
           txtConfirmarSenha.setVisible(false);

       }
       else{
           String senha = txtSenhaCheck.getText();
           txtSenha.setText(senha);
           txtSenhaCheck.setVisible(false);
           txtSenha.setVisible(true);
           
           String login = txtLoginCheck.getText();
           txtLogin.setText(login);
           txtLoginCheck.setVisible(false);
           txtLogin.setVisible(true);
           
           String confirmarSenha = txtConfirmarCheck.getText();
           txtConfirmarSenha.setText(confirmarSenha);
           txtConfirmarCheck.setVisible(false);
           txtConfirmarSenha.setVisible(true);
       }
    }
    
    @FXML
    private void btnExibir_Click(ActionEvent event) throws SQLException {
        String cpfCliente = txtCPF.getText();
        
        cliente = moveModelToView(cpfCliente);
    }

    @FXML
    private void btnCadastrar_Click(ActionEvent event) throws SQLException {
        if(!todosCamposPreenchidos()){
            return;
        }
        
        cliente = moveViewToModel();
        
        //VERIFICANDO
        LoginDAO Log = new LoginDAO();
        CadastroClienteDAO Cadastro = new CadastroClienteDAO();

        boolean contaExiste = Cadastro.contaExiste(cliente);
        
        if(contaExiste){
            alert("Já existe uma conta cadastrada com esse Login.");
        } else{
            //GRAVANDO
            try {
                if(Cadastro.insereCadastro(cliente) == true){
                    Log.insertlogin(cliente);
                    info("Cliente cadastrado com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao cadastrar cliente!");
                }
            } catch (SQLException ex) {
                System.out.println("Deu erro: " + 
                        ex.getMessage());
            //}
            }
        }
    }
    
    @FXML
    private void btnAtualizar_Click(ActionEvent event) throws SQLException {
        if(!todosCamposPreenchidos()){
            return;
        }
        
        String cpfCliente = txtCPF.getText();

        cliente.setCpf(cpfCliente);
        cliente = moveViewToModel();
        LoginDAO Log = new LoginDAO();
        
        //CadastroFornecedor fornecedor = new CadastroFornecedor(); AQUI ESTA O ERRO
        CadastroClienteDAO Cadastro = new CadastroClienteDAO();
        //Cadastro.buscaID(cnpjFornecedor);
        //boolean contaExiste = Cadastro.contaExiste(cnpjFornecedor);
        
        //if(contaExiste){
            try {
                if(Cadastro.altera(cliente) == true){    
                    Log.altera(cliente);
                    info("Fornecedor alterado com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao alterar dados do cliente!");
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
        
        String cpfCliente = txtCPF.getText();
        
        cliente.setCpf(cpfCliente);
        cliente = moveViewToModel();
        LoginDAO Log = new LoginDAO();
        
        //CadastroFornecedor fornecedor = new CadastroFornecedor(); AQUI ESTA O ERRO
        CadastroClienteDAO Cadastro = new CadastroClienteDAO();
        //Cadastro.buscaID(cnpjFornecedor);
        //boolean contaExiste = Cadastro.contaExiste(cnpjFornecedor);
        
        //if(contaExiste){
            try {
                if(Cadastro.remove(cliente) == true){    
                    Log.remove(cliente);
                    info("Excluido com sucesso!");
                    limparCampos();
                }
                else{
                    alert("Erro ao remover dados do cliente!");
                }
            }
            catch (SQLException ex) {
                alert("Deu erro na exclusão");
                System.out.println("Deu erro na exclusão: " + 
                        ex.getMessage());
            }
    }
    
    @FXML
    private void btnLogin_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/login.fxml");
    }
    
    
    private CadastroCliente moveViewToModel(){
        
        cliente = new CadastroCliente();
        cliente.setNome(txtNome.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setLogin(txtLogin.getText());
        cliente.setSenha(txtSenha.getText());
        //armazenando data
        LocalDate dataNascimento = dtDataNascimento.getValue();
        String dataNascimentoString = dataNascimento.toString();
        cliente.setDataNascimento(dataNascimentoString);
        cliente.setGenero(cmbGenero.getValue());
        
        return cliente;
    }
    
    private CadastroCliente moveModelToView(String cpf) throws SQLException {
        CadastroClienteDAO FornDAO = new CadastroClienteDAO();
        //CadastroFornecedor Cadastro = new CadastroFornecedor();
        
        CadastroCliente Cadastro = FornDAO.buscaID(cpf);
        
        //CadastroFornecedor CadastroEncontrado = FornDAO.buscaID(cnpj);
        
        if (Cadastro != null) {
            //txtCPF.setText(String.valueOf(Cadastro.getCnpj()));
            txtCPF.setText(Cadastro.getCpf());
            txtEmail.setText(Cadastro.getEmail());
            txtNome.setText(Cadastro.getNome());
            txtTelefone.setText(Cadastro.getTelefone());
            txtLogin.setText(Cadastro.getLogin());
            txtSenha.setText(Cadastro.getSenha());
            txtConfirmarSenha.setText(Cadastro.getSenha());
            cmbGenero.setValue(Cadastro.getGenero());
            dtDataNascimento.setValue(LocalDate.parse(Cadastro.getDataNascimento()));
            info("Dados encontrados!");
        } else {
            alert("Produto não encontrado.");
        } // Trate a exceção de maneira específica
        // Re-lançar a exceção para tratamento em camadas superiores (ou pode ser tratada de maneira diferente conforme necessário)
        return Cadastro;
    }
    
    private void info(String msg){    
        Alert alerta = new Alert (Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
               
        alerta.showAndWait(); //exibe mensagem
    }
    
    private void alert(String msg){     
        Alert alerta = new Alert (Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(msg);
               
        alerta.showAndWait(); //exibe mensagem
    }
    
    private boolean todosCamposPreenchidos() {
    // Adicione todos os campos que você deseja verificar
        if (txtNome.getText().isEmpty()) {
            alert("Preencha o campo Nome.");
            txtNome.requestFocus();
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            alert("Preencha o campo Email.");
            txtEmail.requestFocus();
            return false;
        }
        if (txtCPF.getText().isEmpty()) {
            alert("Preencha o campo CPF.");
            txtCPF.requestFocus();
            return false;
        }
        if (dtDataNascimento.getValue() == null) {
            alert("Preencha o campo Data_Nascimento.");
            dtDataNascimento.requestFocus();
            return false;
        }
        if (txtTelefone.getText().isEmpty()) {
            alert("Preencha o campo Telefone.");
            txtTelefone.requestFocus();
            return false;
        }
        if (txtLogin.getText().isEmpty()) {
            alert("Preencha o campo Login.");
            txtLogin.requestFocus();
            return false;
        }
        if (txtSenha.getText().isEmpty()) {
            alert("Preencha o campo Senha.");
            txtSenha.requestFocus();
            return false;
        }
        if (!txtConfirmarSenha.getText().equals(txtSenha.getText())) {
            alert("Confirmar senha não coincide com o campo Senha");
            txtConfirmarSenha.requestFocus();
            return false;
        }

        return true;
    }
    
    private void limparCampos () {
        txtCPF.clear();
        txtConfirmarSenha.clear();
        txtEmail.clear();
        txtLogin.clear();
        txtNome.clear();
        txtSenha.clear();
        txtTelefone.clear();
        txtNome.requestFocus();
    }   
    
    @FXML
    private void btnVoltar_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/login.fxml");
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
