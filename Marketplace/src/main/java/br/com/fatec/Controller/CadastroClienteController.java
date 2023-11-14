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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class CadastroClienteController implements Initializable {

    @FXML
    private Pane pnBGColor;
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
    private RadioButton rbtnFeminino;
    @FXML
    private RadioButton rbtnMasculino;
    @FXML
    private RadioButton rbtnNaoEspecificar;
    @FXML
    private CheckBox chkVisualizar;
    @FXML
    private PasswordField txtConfirmarSenha;
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
    private void chkVisualizar_Click(ActionEvent event) {
    }

    @FXML
    private void btnCadastrar_Cliente_Click(ActionEvent event) {
    }

    @FXML
    private void btnLimpar_Click(ActionEvent event) {
    }
    
}
