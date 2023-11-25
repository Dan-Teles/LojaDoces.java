/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author danie
 */
public class AdminController {

    @FXML
    private Button btnFornecedor;
    @FXML
    private Button btnCliente;
    @FXML
    private AnchorPane btnItem;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnProduto;
    @FXML
    private Button btnCarrinho;
    @FXML
    private Button btnCadastrarProduto;

    @FXML
    private void btnFornecedor_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/cadastroFornecedor.fxml");
    }

    @FXML
    private void btnCliente_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/cadastroCliente.fxml");
    }

    @FXML
    private void btnLogin_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/login.fxml");
    }

    @FXML
    private void btnProduto_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/produto.fxml");
    }

    @FXML
    private void btnCarrinho_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/carrinho.fxml");
    }

    @FXML
    private void btnCadastrarProduto_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/cadastroProduto.fxml");
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
