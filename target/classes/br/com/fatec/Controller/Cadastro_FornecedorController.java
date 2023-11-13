/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class Cadastro_FornecedorController implements Initializable {

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
    }

    @FXML
    private void btnLimpar_Click(ActionEvent event) {
    }
    
}
