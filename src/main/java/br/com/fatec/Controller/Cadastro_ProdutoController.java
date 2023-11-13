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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class Cadastro_ProdutoController implements Initializable {

    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnLimpar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtDescricao;
    @FXML
    private ComboBox<?> cboxCategoria;
    @FXML
    private CheckBox chkLactose;
    @FXML
    private CheckBox chkGluten;
    @FXML
    private TextField txtEstoque;
    @FXML
    private TextField txtPreco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCadastrar_Cliente_Click(ActionEvent event) {
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
    }

    @FXML
    private void btnDeletar_Click(ActionEvent event) {
    }

    @FXML
    private void btnLimpar_Click(ActionEvent event) {
    }
    
}
