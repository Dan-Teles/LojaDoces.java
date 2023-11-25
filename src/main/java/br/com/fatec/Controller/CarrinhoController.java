/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.model.Carrinho;
import static br.com.fatec.persistencia.Banco.connect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class CarrinhoController implements Initializable {
    
    @FXML
    private TableColumn<Carrinho, Integer> idPedido;
    @FXML
    private TableColumn<Carrinho, String> produto;
    @FXML
    private TableColumn<Carrinho, Float> quantidade;
    @FXML
    private TableColumn<Carrinho, Float> valor;
    @FXML
    private TableView<Carrinho> tableView;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnLogin;
    @FXML
    private TableColumn<Carrinho, String> login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        produto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        // Carregar os dados do banco de dados e exibir na TableView
        carregarDadosDoBanco();
    }    
    
    private void carregarDadosDoBanco() {
        ObservableList<Carrinho> dadosCarrinho = FXCollections.observableArrayList();

        // Conectar ao banco de dados
        try (Connection conn = connect()) {
            String query = "SELECT idPedido, login, produto, quantidade, valor, valorTotal, fornecedor FROM carrinho";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        int idPedido = rs.getInt("idPedido");
                        String login = rs.getString("login");
                        String produto = rs.getString("produto");
                        float quantidade = rs.getFloat("quantidade");
                        float valor = rs.getFloat("valor");
                        float valorTotal = rs.getFloat("valorTotal");
                        String fornecedor = rs.getString("fornecedor");

                        Carrinho item = new Carrinho(idPedido, login, produto, quantidade, valor, valorTotal, fornecedor);
                        dadosCarrinho.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção de acordo com sua lógica de tratamento de erros
        }

        // Configurar os dados da TableView
        tableView.setItems(dadosCarrinho);
    }

    @FXML
    private void btnVoltar_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/produto.fxml");
    }

    @FXML
    private void btnLogin_Click(ActionEvent event) {
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
