/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.CadastroFornecedorDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private ComboBox<?> cboxEstado;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnLimpar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCadastrar_Click(ActionEvent event) {
        /**fornecedor = moveViewToModel();
        
        CadastroFornecedorDAO fornecedorDAO = new CadastroFornecedorDAO();
        
        try {
                if(DistriDAO.insertCadastro(distribuidora)){
                    msg_info("Cadastro concluido com sucesso!");               
                }
            } catch (SQLException ex) {
                System.out.println("Deu erro: " + 
                        ex.getMessage());
        }*/
    }
    
    private void msg_info(String msg){    
        Alert alerta = new Alert (Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
               
        alerta.showAndWait(); //exibe mensagem
    }
    
    private void msg_alert(String msg){     
        Alert alerta = new Alert (Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(msg);
               
        alerta.showAndWait(); //exibe mensagem
    }
    /**
    private Distribuidora moveViewToModel(){
        
        //CRIA O OBJETO CADASTRO - (MODEL)
        fornecedor = new CadastroFornecedor();
        fornecedor.setNome(txt_nome.getText());
        fornecedor.setCnpj(txt_cnpj.getText());
        fornecedor.setResponsavel(txt_responsavel.getText());
        fornecedor.setEmail(txt_email.getText());
        fornecedor.setCelular(txt_celular.getText());
        fornecedor.setWhats(txt_whats.getText());
                   
        //Devolve o model
        return distribuidora;     
    }*/

    @FXML
    private void btnLimpar_Click(ActionEvent event) {
    }
    
}
