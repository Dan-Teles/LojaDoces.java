/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.CadastroClienteDAO;
import br.com.fatec.DAO.LoginDAO;
import br.com.fatec.model.CadastroCliente;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
    private Button btnLimpar;
    @FXML
    private DatePicker dtDataNascimento;
    @FXML
    private ComboBox<String> cmbGenero;
    
    /**
    @FXML
    private GridPane tblCliente;
    private TableColumn<CadastroCliente, Boolean> colSelecionado;
    private TableColumn<CadastroCliente, Integer> colCpf;
    private TableColumn<CadastroCliente, String> colNome;
    private TableColumn<CadastroCliente, String> colTelefone;
    private TableColumn<CadastroCliente, String> colEmail;
    private TableColumn<CadastroCliente, String> colDataNascimento;
    private TableColumn<CadastroCliente, String> colGenero;
    private TableColumn<CadastroCliente, String> colLogin;
    private TableColumn<CadastroCliente, String> colSenha;
    @FXML
    private Pane pnCliente**/
    
    //variavel
    CadastroCliente cliente = new CadastroCliente();

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        //cb_regiao.getItems().addAll("Zona Norte","Zona Sul", "Zona Leste", "Zona Oeste", "Centro");
        cmbGenero.getItems().addAll("MASCULINO", "FEMININO", "NAO DECLARAR");
        //
    }
   

    /**
     * Initializes the controller class.
     */

    @FXML
    private void chkVisualizar_Click(ActionEvent event) {
    }

    @FXML
    private void btnCadastrar_Cliente_Click(ActionEvent event) throws SQLException {
        cliente = moveViewToModel();
        
        if(!todosCamposPreenchidos()){
            return;
        }
        
        //VERIFICANDO
        LoginDAO Log = new LoginDAO();
        CadastroClienteDAO Cadastro = new CadastroClienteDAO();

        boolean contaExiste = Cadastro.contaExiste(cliente);
        
        if(contaExiste){
            alert("Já existe uma conta cadastrada com esse e-mail.");
        } else{
            //GRAVANDO
            try {
                if(Cadastro.insereCadastro(cliente)){
                    Log.insertlogin(cliente);
                    info("Cadastro concluido com sucesso!");               
                    
                }
            } catch (SQLException ex) {
                System.out.println("Deu erro: " + 
                        ex.getMessage());
            //}
            }
        }
    }
    @FXML
    private void btnLimpar_Click(ActionEvent event) {
        txtCPF.clear();
        txtConfirmarSenha.clear();
        txtEmail.clear();
        txtLogin.clear();
        txtNome.clear();
        txtSenha.clear();
        txtTelefone.clear();
        txtNome.requestFocus();
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
    
}
