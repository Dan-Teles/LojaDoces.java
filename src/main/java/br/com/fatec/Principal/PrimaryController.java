/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author danie
 */
public class PrimaryController implements Initializable {


    @FXML
    private TextField txtLogin;
    @FXML
    private CheckBox chbVisualizar;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnCriarConta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnEntrar_click(ActionEvent event) {
    }

    @FXML
    private void btnCriarConta_Click(ActionEvent event) {
    }

}
